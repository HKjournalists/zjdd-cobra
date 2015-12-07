package com.zaijiadd.app.applyflow.dto;

import java.sql.Timestamp;

public class StoreInfoDTO {
	
	private Long storeId;
	
	private Long shopId;

	private String applyName;

    private String phone;

    private Integer applyType;
   
    private Timestamp applicationTime;
    
    private Timestamp addressApprovalTime;
    
    private Timestamp imgsApprovalTime;
    
    private Timestamp applicationShopTime;
    
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}


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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
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