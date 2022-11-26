package com.udacity.jwdnd.bettysavio.course1.cloudstorage.controller;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private UserService userService;
    private NoteService noteService;
    private AuthenticationService authenticationService;

    public NoteController(AuthenticationService authenticationService, UserService userService, NoteService noteService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/add-note")
    public String addNewNote(Authentication authentication, NoteForm noteForm, Model model) {
        String userName = authentication.getName();
        Integer userId = userService.getUser(userName).getUserid();
        Integer noteId = noteForm.getNoteId(); // Note ID of Form Backing Object
        if (userId != null) {
            if (noteId != null) {  // If that noteId NOT null
                Notes notes = noteService.getNote(noteId); // Does that noteId exists in the DB?
                if (notes.getNoteId().equals(noteId)) { //If yes && same as note Id in the form
                    if (noteService.updateNote(noteForm, noteId, userId) > 0) // update the Note
                        return "redirect:/result?success";
                }
            } else {   // note id is null
                if (noteService.addNewNote(noteForm, userId) > 0) {
                    return "redirect:/result?success";
                }
            }
        }
        return "redirect:/result?error";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNotes(@PathVariable Integer noteId) {
        if (noteService.deleteNote(noteId) > 0) {
            return "redirect:/result?success";
        } else
            return "redirect:/result?error";
    }

}