package com.zaijiadd.app.applyflow.entity;

import java.math.BigDecimal;

public class City {

	private Integer cityId;

	private String cityName;

	private Integer provinceId;
	private Integer totalDealership;
	private Integer alreadySoldNum;
	private Integer depositNum;
	
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

	private BigDecimal cityMoney;
	
	

	public BigDecimal getCityMoney() {
		return cityMoney;
	}

	public void setCityMoney(BigDecimal cityMoney) {
		this.cityMoney = cityMoney;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
}
