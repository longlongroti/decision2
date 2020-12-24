package com.shanxi.coal.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Date;

public class ManageLeaderGroup extends CommonBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @ColumnWidth(40)
    @ExcelProperty(value = {"唯一标识"}, index = 5)
    private String uuid;
    @ColumnWidth(20)
    @ExcelProperty(value = {"所属领导班子"}, index = 1)
    private String leaderGroup;
    @ColumnWidth(20)
    @ExcelProperty(value = {"职位名称"}, index = 2)
    private String jobTitle;
    @ColumnWidth(20)
    @ExcelProperty(value = {"任职开始日期"}, index = 3)
    private String officeStartDate;
    @ColumnWidth(20)
    @ExcelProperty(value = {"任职结束日期"}, index = 4)
    private String officeEndDate;
    @ColumnWidth(40)
    @ExcelProperty(value = {"领导唯一标识"}, index = 0)
    private String leaderId;

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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