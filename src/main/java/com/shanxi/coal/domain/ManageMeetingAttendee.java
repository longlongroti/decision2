package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageMeetingAttendee extends CommonBean implements Serializable {
    private String meetingId;

    private String leaderId;

    private ManageLeader manageLeader;

    public ManageLeader getManageLeader() {
        return manageLeader;
    }

    public void setManageLeader(ManageLeader manageLeader) {
        this.manageLeader = manageLeader;
    }

    private static final long serialVersionUID = 1L;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId == null ? null : meetingId.trim();
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }
}