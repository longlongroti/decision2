package com.shanxi.coal.domain;

import com.alibaba.excel.annotation.ExcelIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageEventsList extends CommonBean implements Serializable {
    private String listName;

    private String versionNumber;

    private String startTime;

    private String endTime;

    private List<ManageEventsDetails> item;

    private List<FileUploaded> fileUploads;

    private String fileIds;

    public List<FileUploaded> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUploaded> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public List<ManageEventsDetails> getItem() {
        return item;
    }

    public void setItem(List<ManageEventsDetails> item) {
        this.item = item;
    }

    private static final long serialVersionUID = 1L;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber == null ? null : versionNumber.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }
}