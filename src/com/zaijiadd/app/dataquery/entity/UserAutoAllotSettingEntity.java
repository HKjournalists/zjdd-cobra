package com.zaijiadd.app.dataquery.entity;

public class UserAutoAllotSettingEntity {
	
	private Integer autoAllotSettingId;
	private Integer userId;
	private Integer allotUserId;
	private Integer status;
	private Integer sortNum;
	private String batchNum;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAllotUserId() {
		return allotUserId;
	}
	public void setAllotUserId(Integer allotUserId) {
		this.allotUserId = allotUserId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public Integer getAutoAllotSettingId() {
		return autoAllotSettingId;
	}
	public void setAutoAllotSettingId(Integer autoAllotSettingId) {
		this.autoAllotSettingId = autoAllotSettingId;
	}

}
