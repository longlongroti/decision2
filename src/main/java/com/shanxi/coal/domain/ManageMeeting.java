package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class ManageMeeting extends CommonBean implements Serializable {
    private String meetingType;

    private String meetingName;

    private String versionNum;

    private String serialNum;

    private String meetingForm;

    private String meetingTime;

    private String summaryTime;

    private String meetingSite;

    private String compereId;

    private String compereName;

    private String attendees;

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

    private List<ManageMeetingAttendee> manageMeetingAttendee;

    private List<ManageMeetingItems> manageMeetingItems;

    public List<ManageMeetingItems> getManageMeetingItems() {
        return manageMeetingItems;
    }

    public void setManageMeetingItems(List<ManageMeetingItems> manageMeetingItems) {
        this.manageMeetingItems = manageMeetingItems;
    }

    public List<ManageMeetingAttendee> getManageMeetingAttendee() {
        return manageMeetingAttendee;
    }

    public void setManageMeetingAttendee(List<ManageMeetingAttendee> manageMeetingAttendee) {
        this.manageMeetingAttendee = manageMeetingAttendee;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    private static final long serialVersionUID = 1L;

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType == null ? null : meetingType.trim();
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName == null ? null : meetingName.trim();
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum == null ? null : versionNum.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getMeetingForm() {
        return meetingForm;
    }

    public void setMeetingForm(String meetingForm) {
        this.meetingForm = meetingForm == null ? null : meetingForm.trim();
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime == null ? null : meetingTime.trim();
    }

    public String getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(String summaryTime) {
        this.summaryTime = summaryTime == null ? null : summaryTime.trim();
    }

    public String getMeetingSite() {
        return meetingSite;
    }

    public void setMeetingSite(String meetingSite) {
        this.meetingSite = meetingSite == null ? null : meetingSite.trim();
    }

    public String getCompereId() {
        return compereId;
    }

    public void setCompereId(String compereId) {
        this.compereId = compereId == null ? null : compereId.trim();
    }

    public String getCompereName() {
        return compereName;
    }

    public void setCompereName(String compereName) {
        this.compereName = compereName == null ? null : compereName.trim();
    }
}