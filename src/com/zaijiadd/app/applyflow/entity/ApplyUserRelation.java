package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class ApplyUserRelation {

	private Integer applyUserRelationId;

	private Integer userid;

	private Integer roleid;

	private Date caurCreatedDate;

	private Date caurUpdatedDate;

	private Integer applyId;

	private Integer caurApplyState;

	private Integer caurApproveState;

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

	public Date getCaurCreatedDate() {
		return caurCreatedDate;
	}

	public void setCaurCreatedDate(Date caurCreatedDate) {
		this.caurCreatedDate = caurCreatedDate;
	}

	public Date getCaurUpdatedDate() {
		return caurUpdatedDate;
	}

	public void setCaurUpdatedDate(Date caurUpdatedDate) {
		this.caurUpdatedDate = caurUpdatedDate;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getCaurApplyState() {
		return caurApplyState;
	}

	public void setCaurApplyState(Integer caurApplyState) {
		this.caurApplyState = caurApplyState;
	}

	public Integer getCaurApproveState() {
		return caurApproveState;
	}

	public void setCaurApproveState(Integer caurApproveState) {
		this.caurApproveState = caurApproveState;
	}
}
