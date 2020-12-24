package com.shanxi.coal.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Date;

public class ManageSystemItems extends CommonBean implements Serializable {
    @ColumnWidth(40)
    @ExcelProperty(value = {"事项名称"}, index = 3)
    private String itemsName;//事项名称
    @ColumnWidth(40)
    @ExcelProperty(value = {"表决方式"}, index = 1)
    private String votingFormula;//表决方式
    @ColumnWidth(40)
    @ExcelProperty(value = {"人数占比"}, index = 2)
    private String peopleCount;//人数占比
    @ColumnWidth(40)
    @ExcelProperty(value = {"制度唯一标识"}, index = 0)
    private String systemParentId;
    @ColumnWidth(40)
    @ExcelProperty(value = {"唯一标识"}, index = 4)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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