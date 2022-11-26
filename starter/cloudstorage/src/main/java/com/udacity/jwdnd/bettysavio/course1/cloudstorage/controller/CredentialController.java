package com.udacity.jwdnd.bettysavio.course1.cloudstorage.controller;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService=userService;
    }

    @GetMapping("/decrypt-password")
    public String decryptPassword(CredentialForm credentialForm, Model model){
        System.out.println("I want to decrypt password from controller");
        return "/home";
    }

    @PostMapping("/add-credential")
    public String addCredential(Authentication authentication, CredentialForm credentialForm, Model model){
        if (credentialForm.getCredentialId()==null) {
            if (credentialService.insertCredentials(credentialForm,userService.getUser(authentication.getName()).getUserid())>0)
                return "redirect:/result?success";
        }
        else {
            Integer userid = userService.getUser(authentication.getName()).getUserid();
            if (credentialService.updateCredentials(credentialForm,userid ) > 0)
                return "redirect:/result?success";
        }
           return "redirect:/result?error";
    }

    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredentials(@PathVariable Integer credentialId){
        if (credentialService.deleteCredentials(credentialId)>0)
            return "redirect:/result?success";
        return "redirect:/result?error";
    }



}
