package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ManageEventsDetails extends CommonBean implements Serializable {
    private String parentId;

    private String eventId;

    private List<ManageEventsDetailItem> manageEventsDetailItems;

    public List<ManageEventsDetailItem> getManageEventsDetailItems() {
        return manageEventsDetailItems;
    }

    public void setManageEventsDetailItems(List<ManageEventsDetailItem> manageEventsDetailItems) {
        this.manageEventsDetailItems = manageEventsDetailItems;
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

    DicEventsCatalog dicEventsCatalogs;

    public DicEventsCatalog getDicEventsCatalogs() {
        return dicEventsCatalogs;
    }

    public void setDicEventsCatalogs(DicEventsCatalog dicEventsCatalogs) {
        this.dicEventsCatalogs = dicEventsCatalogs;
    }
}