package com.udacity.jwdnd.bettysavio.course1.cloudstorage.model;

public class Files {
    private Integer userid;
    private Integer fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private byte[] fileData;

    public Files(Integer fileId, String filename, String contentType, String fileSize,Integer userId, byte[] fileData) {
        this.userid = userId;
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }
    // Spring MVC uses Model class getter and Setter methods to bind data between Model View and Controller

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
