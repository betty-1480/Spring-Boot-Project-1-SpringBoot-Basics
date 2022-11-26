package com.udacity.jwdnd.bettysavio.course1.cloudstorage.controller;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String  uploadFile(Authentication authentication, @RequestParam("fileUpload")MultipartFile multipartFile, Model model) throws IOException {
            String userName=authentication.getName();
            Integer userid=userService.getUser(userName).getUserid();
            if (!fileService.getAllFileNames(userid).contains(multipartFile.getOriginalFilename())){
                double fileSize=multipartFile.getSize();
                if(fileService.uploadFile(multipartFile,userid)>0){
                    return "redirect:/result?success";
                }
            }
            return "redirect:/result?error";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId){
        if (fileService.deleteFile(fileId)>0)
            return "redirect:/result?success";
        else
            return "redirect:/result?error";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
       Files file= fileService.getFileByFileId(fileId);
       //return new ResponseEntity<>(new ByteArrayResource(file.getFileData()), HttpStatus.OK);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(new ByteArrayResource(file.getFileData()));
    }

}
