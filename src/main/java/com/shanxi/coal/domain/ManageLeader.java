package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageLeader extends CommonBean implements Serializable {
    private String leaderName;

    private String leaderSort;

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