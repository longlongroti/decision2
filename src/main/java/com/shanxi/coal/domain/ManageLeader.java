package com.shanxi.coal.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageLeader extends CommonBean implements Serializable {
    @ColumnWidth(40)
    @ExcelProperty(value = {"领导唯一标识"}, index = 0)
    private String uuid;
    @ColumnWidth(20)
    @ExcelProperty(value = {"领导姓名"}, index = 1)
    private String leaderName;
    @ColumnWidth(20)
    @ExcelProperty(value = {"排序"}, index = 2)
    private String leaderSort;
    @ColumnWidth(20)
    @ExcelProperty(value = {"备注"}, index = 3)
    private String remark;
    @ExcelIgnore
    private List<FileUploaded> fileUploads;
    @ExcelIgnore
    private String fileIds;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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