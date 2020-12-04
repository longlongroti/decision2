package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageEventsList extends CommonBean implements Serializable {
    private String listName;

    private String versionNumber;

    private String startTime;

    private String endTime;

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