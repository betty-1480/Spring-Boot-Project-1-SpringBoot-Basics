package com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from Users where userName=#{userName}")
    User getUserByName(String userName);

/*    @Insert("insert into Users(username, salt, password, firstname, lastname) values(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty ="userId")
    int createUser(String username,String salt,String password, String  firstname, String lastname );*/

    @Insert("insert into Users(username, salt, password, firstname, lastname) values(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty ="userId")
    int createUser(User user);

}
