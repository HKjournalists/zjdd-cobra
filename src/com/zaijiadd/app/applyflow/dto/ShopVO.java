package com.zaijiadd.app.applyflow.dto;

import java.sql.Timestamp;

public class ShopVO extends StoreInfoVO{
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = -6931549750342434254L;

	private Long shopId;

	private Integer shopApplicant;

    private Timestamp imgsApprovalTime;

    private Integer imgsApprover;

    private Integer imgsAuditStatus;
    
    private String imgsAuditOpinion;
    
    private Timestamp applicationShopTime;
    
    private int isHistory;
    
    private String username;
    
    private String password;
    
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


    public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public int getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(int isHistory) {
		this.isHistory = isHistory;
	}


}
