package com.udacity.jwdnd.bettysavio.course1.cloudstorage.services;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<Files> getAllFiles(Integer userId){
        return fileMapper.getAllFiles(userId);
    }

    public Files getFileByFileId(Integer fileId){
        return fileMapper.getFilesByFileId(fileId);
    }

    public List<String> getAllFileNames(Integer userid){
        return fileMapper.getAllFileNames(userid);
    }

    public Integer uploadFile(MultipartFile multipartFile, int userid) throws IOException {
       return fileMapper.uploadFile(new Files(null,multipartFile.getOriginalFilename(), multipartFile.getContentType(),String.valueOf(multipartFile.getSize()),userid, multipartFile.getBytes()));
    }

    public Integer deleteFile(Integer fileId){
       return fileMapper.deleteFile(fileId);
    }

}
