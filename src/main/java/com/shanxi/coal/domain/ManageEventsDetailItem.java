package com.shanxi.coal.domain;

import java.io.Serializable;

public class ManageEventsDetailItem extends CommonBean implements Serializable {
    private String parentId;

    private String eventId;

    private String eventName;

    private String eventCode;

    private String decisionSequence;
    private String decisionSequence2;
    private String decisionSequence3;

    private String isLegalReview;

    public String getDecisionSequence2() {
        return decisionSequence2;
    }

    public void setDecisionSequence2(String decisionSequence2) {
        this.decisionSequence2 = decisionSequence2;
    }

    public String getDecisionSequence3() {
        return decisionSequence3;
    }

    public void setDecisionSequence3(String decisionSequence3) {
        this.decisionSequence3 = decisionSequence3;
    }

    private static final long serialVersionUID = 1L;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    public String getDecisionSequence() {
        return decisionSequence;
    }

    public void setDecisionSequence(String decisionSequence) {
        this.decisionSequence = decisionSequence == null ? null : decisionSequence.trim();
    }

    public String getIsLegalReview() {
        return isLegalReview;
    }

    public void setIsLegalReview(String isLegalReview) {
        this.isLegalReview = isLegalReview == null ? null : isLegalReview.trim();
    }

    DicEventsCatalog dicEventsCatalogs;

    public DicEventsCatalog getDicEventsCatalogs() {
        return dicEventsCatalogs;
    }

    public void setDicEventsCatalogs(DicEventsCatalog dicEventsCatalogs) {
        this.dicEventsCatalogs = dicEventsCatalogs;
    }
}