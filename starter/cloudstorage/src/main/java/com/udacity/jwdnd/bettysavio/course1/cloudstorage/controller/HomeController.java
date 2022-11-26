package com.udacity.jwdnd.bettysavio.course1.cloudstorage.controller;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.User;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private AuthenticationService authenticationService;
    private EncryptionService encryptionServic;

   public HomeController(UserService userService,EncryptionService encryptionService, NoteService noteService,FileService fileService,CredentialService credentialService,AuthenticationService authenticationService){
        this.userService=userService;
        this.encryptionServic=encryptionService;
        this.noteService=noteService;
        this.authenticationService=authenticationService;
        this.fileService=fileService;
        this.credentialService=credentialService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model){
        String userName=authentication.getName();
        User user=userService.getUser(userName);
        Integer userId=user.getUserid();
        model.addAttribute("notes",noteService.getAllNotes(userId));
        model.addAttribute("files",fileService.getAllFiles(userId));
        model.addAttribute("credentials",credentialService.getAllCredentials(userId));
        model.addAttribute("encryptionService",encryptionServic);
        return "home";
    }



}
