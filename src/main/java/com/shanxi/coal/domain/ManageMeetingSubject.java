package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageMeetingSubject extends CommonBean implements Serializable {

    private String remark;

    private String meetingId;

    private String subjectCode;

    private String subjectName;

    private String sourceName;

    private String specialName;

    private String passFlag;

    private String approvalFlag;

    private String adoptFlag;

    private String superviseFlag;

    private String subjectResult;

    private List<FileUploaded> fileUploads;

    private String fileIds;


    private static final long serialVersionUID = 1L;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId == null ? null : meetingId.trim();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName == null ? null : specialName.trim();
    }

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag == null ? null : passFlag.trim();
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag == null ? null : approvalFlag.trim();
    }

    public String getAdoptFlag() {
        return adoptFlag;
    }

    public void setAdoptFlag(String adoptFlag) {
        this.adoptFlag = adoptFlag == null ? null : adoptFlag.trim();
    }

    public String getSuperviseFlag() {
        return superviseFlag;
    }

    public void setSuperviseFlag(String superviseFlag) {
        this.superviseFlag = superviseFlag == null ? null : superviseFlag.trim();
    }

    public String getSubjectResult() {
        return subjectResult;
    }

    public void setSubjectResult(String subjectResult) {
        this.subjectResult = subjectResult == null ? null : subjectResult.trim();
    }
}