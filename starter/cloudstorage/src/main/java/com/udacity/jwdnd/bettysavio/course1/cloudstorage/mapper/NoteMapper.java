package com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("select * from Notes where userid=#{userId}")
    public List<Notes> getAllNotes(Integer userId);

    @Select("select * from Notes where noteid=#{noteId}")
    public Notes getNote(Integer noteId);

    @Insert("insert into Notes (noteTitle,noteDescription,userid) values(#{noteTitle},#{noteDescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty ="noteId")
    public int insertNote(Notes notes);

    @Update("update Notes set noteTitle=#{noteTitle},noteDescription=#{noteDescription} where noteid=#{noteId}")
    public int updateNote(Notes notes);

    @Delete("delete from Notes where noteid=#{noteId}")
    public int deleteNote(Integer noteId);
}
