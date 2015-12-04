package com.zaijiadd.app.applyflow.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CityDealership {
    private Integer cityDealershipId;

    private String cityType;

    private Integer cityId;

    private Integer sellDealershipNum;

    private BigDecimal cityDealershipMoney;

    private Date createdDate;

    private Date updatedDate;

    public Integer getCityDealershipId() {
        return cityDealershipId;
    }

    public void setCityDealershipId(Integer cityDealershipId) {
        this.cityDealershipId = cityDealershipId;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getSellDealershipNum() {
        return sellDealershipNum;
    }

    public void setSellDealershipNum(Integer sellDealershipNum) {
        this.sellDealershipNum = sellDealershipNum;
    }

    public BigDecimal getCityDealershipMoney() {
        return cityDealershipMoney;
    }

    public void setCityDealershipMoney(BigDecimal cityDealershipMoney) {
        this.cityDealershipMoney = cityDealershipMoney;
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