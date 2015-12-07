package com.zaijiadd.app.applyflow.entity;

import java.sql.Timestamp;

public class ShopApply {
    private Long shopId;

    private Long storeId;

    private Integer shopApplicant;

    private Timestamp imgsApprovalTime;

    private Integer imgsApprover;

    private Integer imgsAuditStatus;
    
    private int isHistory;
    

    public int getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(int isHistory) {
		this.isHistory = isHistory;
	}

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

	public Integer getShopApplicant() {
		return shopApplicant;
	}

	public void setShopApplicant(Integer shopApplicant) {
		this.shopApplicant = shopApplicant;
	}

	public Timestamp getImgsApprovalTime() {
		return imgsApprovalTime;
	}

	public void setImgsApprovalTime(Timestamp imgsApprovalTime) {
		this.imgsApprovalTime = imgsApprovalTime;
	}

	public Integer getImgsApprover() {
		return imgsApprover;
	}

	public void setImgsApprover(Integer imgsApprover) {
		this.imgsApprover = imgsApprover;
	}

	public Integer getImgsAuditStatus() {
		return imgsAuditStatus;
	}

	public void setImgsAuditStatus(Integer imgsAuditStatus) {
		this.imgsAuditStatus = imgsAuditStatus;
	}

	public String getImgsAuditOpinion() {
		return imgsAuditOpinion;
	}

	public void setImgsAuditOpinion(String imgsAuditOpinion) {
		this.imgsAuditOpinion = imgsAuditOpinion;
	}

	public Timestamp getApplicationShopTime() {
		return applicationShopTime;
	}

	public void setApplicationShopTime(Timestamp applicationShopTime) {
		this.applicationShopTime = applicationShopTime;
	}

	private String imgsAuditOpinion;
    
    private Timestamp applicationShopTime;

    
}