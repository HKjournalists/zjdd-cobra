/**
 * @(#)InviteUser.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc. All
 *                     rights reserved.
 */

package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 邀约用户
 * @author chentao
 * @date 2015年11月28日
 */

public class InviteUserEntity {

	private Integer inviteUserid;
	private String inviteuserName;
	private String inviteuserMobile;
	@JSONField(format = "yyyy-MM-dd ")
	private Date visitTime;// 预计到访时间
	private String referrer;// 推荐人
	private int applyRole;// 申请角色 1是经销商 2是小店
	private Integer userState;// 1已邀约 2已到访
	private String remark;
	private Date createDate;
	private Date updatedDate;
	private int yjsUserId;// 用户id
	private String personNumber;// 身份证

	public Integer getInviteUserid() {
		return inviteUserid;
	}

	public void setInviteUserid(Integer inviteUserid) {
		this.inviteUserid = inviteUserid;
	}

	public String getInviteuserName() {
		return inviteuserName;
	}

	public void setInviteuserName(String inviteuserName) {
		this.inviteuserName = inviteuserName;
	}

	public String getInviteuserMobile() {
		return inviteuserMobile;
	}

	public void setInviteuserMobile(String inviteuserMobile) {
		this.inviteuserMobile = inviteuserMobile;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public int getApplyRole() {
		return applyRole;
	}

	public void setApplyRole(int applyRole) {
		this.applyRole = applyRole;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getYjsUserId() {
		return yjsUserId;
	}

	public void setYjsUserId(int yjsUserId) {
		this.yjsUserId = yjsUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
