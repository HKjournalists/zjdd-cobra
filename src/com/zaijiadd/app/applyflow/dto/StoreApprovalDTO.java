package com.zaijiadd.app.applyflow.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StoreApprovalDTO {

	private String applyName;

	private int paymoneyType;

	private BigDecimal paidMoney;

	private Timestamp applicationTime;

	private Timestamp addressApprovalTime;

	private Timestamp imgsApprovalTime;

	private Timestamp applicationShopTime;

	private Long storeId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Timestamp getApplicationShopTime() {
		return applicationShopTime;
	}

	public void setApplicationShopTime(Timestamp applicationShopTime) {
		this.applicationShopTime = applicationShopTime;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public int getPaymoneyType() {
		return paymoneyType;
	}

	public void setPaymoneyType(int paymoneyType) {
		this.paymoneyType = paymoneyType;
	}

	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	public Timestamp getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Timestamp applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Timestamp getAddressApprovalTime() {
		return addressApprovalTime;
	}

	public void setAddressApprovalTime(Timestamp addressApprovalTime) {
		this.addressApprovalTime = addressApprovalTime;
	}

	public Timestamp getImgsApprovalTime() {
		return imgsApprovalTime;
	}

	public void setImgsApprovalTime(Timestamp imgsApprovalTime) {
		this.imgsApprovalTime = imgsApprovalTime;
	}

}