package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageEventsDetails extends CommonBean implements Serializable {
    private String parentId;

    private String eventId;

    private String eventName;

    private String eventCode;

    private String decisionSequence;

    private String isLegalReview;

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

    private List<DicEventsCatalog> dicEventsCatalogs;

    public List<DicEventsCatalog> getDicEventsCatalogs() {
        return dicEventsCatalogs;
    }

    public void setDicEventsCatalogs(List<DicEventsCatalog> dicEventsCatalogs) {
        this.dicEventsCatalogs = dicEventsCatalogs;
    }
}