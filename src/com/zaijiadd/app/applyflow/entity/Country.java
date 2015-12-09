package com.zaijiadd.app.applyflow.entity;

import java.math.BigDecimal;

public class Country {
    private Integer countryId;

    private String countryName;

    private Integer cityId;
    private Integer totalDealership;
	private Integer alreadySoldNum;
	private Integer depositNum;
	private BigDecimal countryMoney;

    

	public BigDecimal getCountryMoney() {
		return countryMoney;
	}

	public void setCountryMoney(BigDecimal countryMoney) {
		this.countryMoney = countryMoney;
	}

	public Integer getTotalDealership() {
		return totalDealership;
	}

	public void setTotalDealership(Integer totalDealership) {
		this.totalDealership = totalDealership;
	}

	public Integer getAlreadySoldNum() {
		return alreadySoldNum;
	}

	public void setAlreadySoldNum(Integer alreadySoldNum) {
		this.alreadySoldNum = alreadySoldNum;
	}

	public Integer getDepositNum() {
		return depositNum;
	}

	public void setDepositNum(Integer depositNum) {
		this.depositNum = depositNum;
	}

	public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}