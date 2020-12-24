package com.shanxi.coal.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelIgnore;

import java.util.Date;
import java.util.List;

public class CommonBean {
    @ExcelIgnore
    private String uuid;
    @ExcelIgnore
    private String createdBy;
    @ExcelIgnore
    private Date createdDate;
    @ExcelIgnore
    private String modifiedBy;
    @ExcelIgnore
    private Date modifiedDate;
    @ExcelIgnore
    private Integer isDel;
    @ExcelIgnore
    private Integer status;
    @ExcelIgnore
    private String createdByOrg;
    @ExcelIgnore
    private List<String> supervisedOrg;
    @ExcelIgnore
    private List<String> currentUsingOrg;
    @ExcelIgnore
    private List<String> currentStorageOrg;
    @ExcelIgnore
    private List<String> currentUsingDept;
    @ExcelIgnore
    private Date createdDateStart;
    @ExcelIgnore
    private Date createdDateEnd;
    @ExcelIgnore
    private String remark;
    @ExcelIgnore
    private String createdByName;
    @ExcelIgnore
    private String createdByOrgName;

    public List<String> getCurrentUsingOrg() {
        return currentUsingOrg;
    }

    public void setCurrentUsingOrg(List<String> currentUsingOrg) {
        this.currentUsingOrg = currentUsingOrg;
    }

    public List<String> getCurrentStorageOrg() {
        return currentStorageOrg;
    }

    public void setCurrentStorageOrg(List<String> currentStorageOrg) {
        this.currentStorageOrg = currentStorageOrg;
    }

    public List<String> getCurrentUsingDept() {
        return currentUsingDept;
    }

    public void setCurrentUsingDept(List<String> currentUsingDept) {
        this.currentUsingDept = currentUsingDept;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByOrgName() {
        return createdByOrgName;
    }

    public void setCreatedByOrgName(String createdByOrgName) {
        this.createdByOrgName = createdByOrgName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedByOrg() {
        return createdByOrg;
    }

    public void setCreatedByOrg(String createdByOrg) {
        this.createdByOrg = createdByOrg;
    }

    public List<String> getSupervisedOrg() {
        return supervisedOrg;
    }

    public void setSupervisedOrg(List<String> supervisedOrg) {
        this.supervisedOrg = supervisedOrg;
    }

    public Date getCreatedDateStart() {
        return createdDateStart;
    }

    public void setCreatedDateStart(Date createdDateStart) {
        this.createdDateStart = createdDateStart;
    }

    public Date getCreatedDateEnd() {
        return createdDateEnd;
    }

    public void setCreatedDateEnd(Date createdDateEnd) {
        this.createdDateEnd = createdDateEnd;
    }
}
