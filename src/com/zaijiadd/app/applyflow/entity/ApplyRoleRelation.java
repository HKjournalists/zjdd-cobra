package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class ApplyRoleRelation {
    private Integer applyRoleRelationId;

    private Integer roleid;

    private Date createdDate;

    private Date updatedDate;

    private Integer applyStoreId;

    private Integer applyState;

    public Integer getApplyRoleRelationId() {
        return applyRoleRelationId;
    }

    public void setApplyRoleRelationId(Integer applyRoleRelationId) {
        this.applyRoleRelationId = applyRoleRelationId;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    public Integer getApplyStoreId() {
        return applyStoreId;
    }

    public void setApplyStoreId(Integer applyStoreId) {
        this.applyStoreId = applyStoreId;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }
}