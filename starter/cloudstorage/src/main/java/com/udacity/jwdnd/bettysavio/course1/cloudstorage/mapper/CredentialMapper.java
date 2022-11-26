package com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("select * from Credentials where userid=#{userid}")
    List<Credentials> getAllCredentials(Integer userid);

    @Select("select * from Credentials where credentialsId=#{credentialsId}")
    Credentials getCredentialById(Integer credentialId);

    @Insert("insert into Credentials(url,username,key,password,userid) values(#{url},#{username},#{key},#{password},#{userid})")
    Integer insertCredentials(Credentials credentials);

    @Update("Update Credentials set url=#{url},username=#{username},key=#{key},password=#{password},userid=#{userid} ")
    Integer updateCredentials(Credentials credentials);

    @Delete("Delete from Credentials where credentialId=#{credentialId}")
    Integer deleteCredentials(Integer credentialId);
}
