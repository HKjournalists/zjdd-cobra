package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class ShopApply {
    private Long shopId;

    private Long storeId;

    private Integer shopApplicant;

    private Date imgsApprovalTime;

    private Integer imgsApprover;

    private Byte imgsAuditStatus;

    private String imgsAuditOpinion;

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

    public Date getImgsApprovalTime() {
        return imgsApprovalTime;
    }

    public void setImgsApprovalTime(Date imgsApprovalTime) {
        this.imgsApprovalTime = imgsApprovalTime;
    }

    public Integer getImgsApprover() {
        return imgsApprover;
    }

    public void setImgsApprover(Integer imgsApprover) {
        this.imgsApprover = imgsApprover;
    }

    public Byte getImgsAuditStatus() {
        return imgsAuditStatus;
    }

    public void setImgsAuditStatus(Byte imgsAuditStatus) {
        this.imgsAuditStatus = imgsAuditStatus;
    }

    public String getImgsAuditOpinion() {
        return imgsAuditOpinion;
    }

    public void setImgsAuditOpinion(String imgsAuditOpinion) {
        this.imgsAuditOpinion = imgsAuditOpinion == null ? null : imgsAuditOpinion.trim();
    }
}