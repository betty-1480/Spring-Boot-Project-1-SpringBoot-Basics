package com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("select * from Files where userid=#{userid}")
    public List<Files> getAllFiles(Integer userid);

    @Select("Select * from Files where fileId=#{fileId}")
    Files getFilesByFileId(Integer fileId);

    @Select("Select filename from Files where userid=#{userid}")
    List<String> getAllFileNames(Integer userid);

    @Insert("insert into Files (filename,contentType, fileSize,userid,fileData) values (#{filename}, #{contentType},#{fileSize},#{userid},#{fileData})")
    public int uploadFile(Files file);

    @Delete("delete from Files where fileId=#{fileId}")
    public Integer deleteFile(Integer fileId);
}
