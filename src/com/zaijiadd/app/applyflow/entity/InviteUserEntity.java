/**
 * @(#)InviteUser.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc. All
 *                     rights reserved.
 */

package com.zaijiadd.app.applyflow.entity;

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
	private String visitTime;// 预计到访时间
	private String referrer;// 推荐人
	private int applyRole;// 申请角色 1是经销商 2是小店
	private Integer userState;// 1已邀约 2已到访
	private String remark;
	private String createDate;
	private String updatedDate;
	private int yjsUserId;// 用户id
	private String personNumber;// 身份证
	private int fuctionSate;// 功能
	private Integer userAddFlag;

	public Integer getUserAddFlag() {
		return userAddFlag;
	}

	public void setUserAddFlag(Integer userAddFlag) {
		this.userAddFlag = userAddFlag;
	}

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

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getCreateDate() {
		return createDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
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

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public int getFuctionSate() {
		return fuctionSate;
	}

	public void setFuctionSate(int fuctionSate) {
		this.fuctionSate = fuctionSate;
	}

}
