package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class Bank {
    private Integer bankMsgId;

    private String bankName;

    private Date bankUpdatedDate;

    public Integer getBankMsgId() {
        return bankMsgId;
    }

    public void setBankMsgId(Integer bankMsgId) {
        this.bankMsgId = bankMsgId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getBankUpdatedDate() {
        return bankUpdatedDate;
    }

    public void setBankUpdatedDate(Date bankUpdatedDate) {
        this.bankUpdatedDate = bankUpdatedDate;
    }
}