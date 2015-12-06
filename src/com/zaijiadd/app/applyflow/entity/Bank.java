package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class Bank {
    private String bankCode;

    private String bankName;

    private String shortName;

    private String bankOrg;

    private Integer useFlag;

    private Integer orderB;

    private Date createdDate;

    private Date updatedDate;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBankOrg() {
        return bankOrg;
    }

    public void setBankOrg(String bankOrg) {
        this.bankOrg = bankOrg;
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Integer getOrderB() {
        return orderB;
    }

    public void setOrderB(Integer orderB) {
        this.orderB = orderB;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}