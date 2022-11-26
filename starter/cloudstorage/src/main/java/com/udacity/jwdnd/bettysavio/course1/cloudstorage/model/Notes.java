package com.udacity.jwdnd.bettysavio.course1.cloudstorage.model;


public class Notes {
   private Integer noteId;
   private String noteTitle;
   private String noteDescription;
   private Integer userid;

    public Notes(Integer noteId, String noteTitle, String noteDescription, Integer userid) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userid = userid;
    }
    // Spring MVC uses Model class getter and Setter methods to bind data between Model View and Controller

    public Integer getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserid() {
        return userid;
    }

}
