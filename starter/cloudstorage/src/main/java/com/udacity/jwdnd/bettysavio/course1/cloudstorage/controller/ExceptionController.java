package com.udacity.jwdnd.bettysavio.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleControllerExceptions (MaxUploadSizeExceededException maxUploadSizeExceededException, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("bigFile",true);
        return "redirect:/home";
    }
}
