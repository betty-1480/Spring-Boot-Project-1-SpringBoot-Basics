package com.udacity.jwdnd.bettysavio.course1.cloudstorage.services;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper){
        this.noteMapper=noteMapper;
    }

    public Notes getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    public List<Notes> getAllNotes(Integer userId){
        return noteMapper.getAllNotes(userId);
    }

    public int addNewNote(NoteForm noteForm, Integer userId){
       int returnValue=noteMapper.insertNote(new Notes(null,noteForm.getNoteTitle(),noteForm.getNoteDescription(),userId));
      return returnValue;
    }

    public int updateNote(NoteForm noteForm,Integer noteId, Integer userId){
        int returnValue=noteMapper.updateNote(new Notes(noteId, noteForm.getNoteTitle(),noteForm.getNoteDescription(),userId));
        return returnValue;
    }

    public int deleteNote(Integer noteId){
       return noteMapper.deleteNote(noteId);
    }

}
