package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class XMLVoteMode {
    private String itemCode;
    private String rate;
    private String voteMode;

    public String getItemCode() {
        return itemCode;
    }
    @XmlElement(name = "item_code")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getRate() {
        return rate;
    }
    @XmlElement(name = "rate")
    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getVoteMode() {
        return voteMode;
    }
    @XmlElement(name = "vote_mode")
    public void setVoteMode(String voteMode) {
        this.voteMode = voteMode;
    }
}
