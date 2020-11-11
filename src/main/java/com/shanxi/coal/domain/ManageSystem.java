package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageSystem extends CommonBean implements Serializable {
    private String systemName;

    private String systemType;

    private String systemTypeId;

    private String approveDate;

    private String effectiveDate;

    private String expiryDate;

    private String isLegalApprove;

    private String meetingType;

    private static final long serialVersionUID = 1L;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType == null ? null : systemType.trim();
    }

    public String getSystemTypeId() {
        return systemTypeId;
    }

    public void setSystemTypeId(String systemTypeId) {
        this.systemTypeId = systemTypeId == null ? null : systemTypeId.trim();
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate == null ? null : approveDate.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate == null ? null : expiryDate.trim();
    }

    public String getIsLegalApprove() {
        return isLegalApprove;
    }

    public void setIsLegalApprove(String isLegalApprove) {
        this.isLegalApprove = isLegalApprove;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }
}