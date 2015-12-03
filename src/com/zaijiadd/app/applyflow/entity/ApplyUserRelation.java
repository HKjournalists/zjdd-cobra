package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class ApplyUserRelation {
    private Integer applyUserRelationId;

    private Integer userid;

    private Integer roleid;

    private Date createdDate;

    private Date updatedDate;

    private Integer applyId;

    private Integer applyState;

    private Integer approveState;

    public Integer getApplyUserRelationId() {
        return applyUserRelationId;
    }

    public void setApplyUserRelationId(Integer applyUserRelationId) {
        this.applyUserRelationId = applyUserRelationId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public Integer getApproveState() {
        return approveState;
    }

    public void setApproveState(Integer approveState) {
        this.approveState = approveState;
    }
}