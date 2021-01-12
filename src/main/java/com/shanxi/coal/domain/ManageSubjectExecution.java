package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class ManageSubjectExecution implements Serializable {
    private String uuid;

    private String subjectId;

    private String subjectCode;

    private String effect;

    private String status;

    private String description;

    private String remark;

    private List<FileUploaded> fileUploads;

    private String fileIds;

    private List<ManageSubjectExecutionDuty> manageSubjectExecutionDuties;

    private static final long serialVersionUID = 1L;

    public List<ManageSubjectExecutionDuty> getManageSubjectExecutionDuties() {
        return manageSubjectExecutionDuties;
    }

    public void setManageSubjectExecutionDuties(List<ManageSubjectExecutionDuty> manageSubjectExecutionDuties) {
        this.manageSubjectExecutionDuties = manageSubjectExecutionDuties;
    }

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect == null ? null : effect.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}