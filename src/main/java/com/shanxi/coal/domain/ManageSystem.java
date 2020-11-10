package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;

public class ManageSystem extends CommonBean implements Serializable {
    private String systemName;//制度名称

    private String systemType;//制度类型

    private String systemTypeId;

    private String approveDate;//审批日期

    private String effectiveDate;//生效

    private String expiryDate;//失效

    private Byte isLegalApprove;//是否经过合法审查

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

    public Byte getIsLegalApprove() {
        return isLegalApprove;
    }

    public void setIsLegalApprove(Byte isLegalApprove) {
        this.isLegalApprove = isLegalApprove;
    }
}