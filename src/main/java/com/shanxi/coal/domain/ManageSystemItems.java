package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageSystemItems extends CommonBean implements Serializable {
    private String itemsName;//事项名称

    private String votingFormula;//表决方式

    private String peopleCount;//人数占比

    private String systemParentId;

    private static final long serialVersionUID = 1L;

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName == null ? null : itemsName.trim();
    }

    public String getVotingFormula() {
        return votingFormula;
    }

    public void setVotingFormula(String votingFormula) {
        this.votingFormula = votingFormula;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount == null ? null : peopleCount.trim();
    }

    public String getSystemParentId() {
        return systemParentId;
    }

    public void setSystemParentId(String systemParentId) {
        this.systemParentId = systemParentId == null ? null : systemParentId.trim();
    }
}