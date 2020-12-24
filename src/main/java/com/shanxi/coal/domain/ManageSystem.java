package com.shanxi.coal.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageSystem extends CommonBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @ColumnWidth(40)
    @ExcelProperty(value = {"制度唯一标识"}, index = 0)
    private String uuid;
    @ColumnWidth(20)
    @ExcelProperty(value = {"制度名称"}, index = 1)
    private String systemName;
    @ColumnWidth(20)
    @ExcelProperty(value = {"制度类型"}, index = 2)
    private String systemType;
    @ExcelIgnore
    private String systemTypeId;
    @ColumnWidth(20)
    @ExcelProperty(value = {"审批日期"}, index = 7)
    private String approveDate;
    @ColumnWidth(20)
    @ExcelProperty(value = {"生效日期"}, index = 3)
    private String effectiveDate;
    @ColumnWidth(20)
    @ExcelProperty(value = {"失效日期"}, index = 4)
    private String expiryDate;
    @ColumnWidth(20)
    @ExcelProperty(value = {"经过合法审查"}, index = 5)
    private String isLegalApprove;
    @ColumnWidth(20)
    @ExcelProperty(value = {"会议类型"}, index = 6)
    private String meetingType;
    @ExcelIgnore
    private List<FileUploaded> fileUploads;
    @ExcelIgnore
    private String fileIds;

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