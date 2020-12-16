package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageLeader extends CommonBean implements Serializable {
    private String leaderName;

    private String leaderSort;

    private List<FileUploaded> fileUploads;

    private String fileIds;

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public List<FileUploaded> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUploaded> fileUploads) {
        this.fileUploads = fileUploads;
    }

    private static final long serialVersionUID = 1L;

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName == null ? null : leaderName.trim();
    }

    public String getLeaderSort() {
        return leaderSort;
    }

    public void setLeaderSort(String leaderSort) {
        this.leaderSort = leaderSort == null ? null : leaderSort.trim();
    }
}