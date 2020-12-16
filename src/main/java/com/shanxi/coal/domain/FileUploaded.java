package com.shanxi.coal.domain;

import java.io.Serializable;

public class FileUploaded extends CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileCategory;
    private String fileCategoryId;
    private String fileType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileCategory() {
        return fileCategory;
    }

    public void setFileCategory(String fileCategory) {
        this.fileCategory = fileCategory;
    }

    public String getFileCategoryId() {
        return fileCategoryId;
    }

    public void setFileCategoryId(String fileCategoryId) {
        this.fileCategoryId = fileCategoryId == null ? null : fileCategoryId.trim();
    }
}
