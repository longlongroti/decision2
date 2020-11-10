package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageLeaderGroup extends CommonBean implements Serializable {
    private String leaderGroup;

    private String jobTitle;

    private String officeStartDate;

    private String officeEndDate;

    private String leaderId;

    private static final long serialVersionUID = 1L;

    public String getLeaderGroup() {
        return leaderGroup;
    }

    public void setLeaderGroup(String leaderGroup) {
        this.leaderGroup = leaderGroup == null ? null : leaderGroup.trim();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    public String getOfficeStartDate() {
        return officeStartDate;
    }

    public void setOfficeStartDate(String officeStartDate) {
        this.officeStartDate = officeStartDate;
    }

    public String getOfficeEndDate() {
        return officeEndDate;
    }

    public void setOfficeEndDate(String officeEndDate) {
        this.officeEndDate = officeEndDate;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }
}