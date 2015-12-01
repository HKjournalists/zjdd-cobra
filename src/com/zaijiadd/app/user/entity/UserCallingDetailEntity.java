package com.zaijiadd.app.user.entity;

import java.util.Date;

public class UserCallingDetailEntity {
	
	private Integer userCallingDetailId;
	private Integer userId;
	private Integer msgId;
	private Date beginDate;
	private Date endDate;
	private Integer holdingTime;
	private String srcMobile;
	private String destMobile;
	private Integer status;
	private String recordUrl;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId( Integer msgId ) {
		this.msgId = msgId;
	}
	public String getDestMobile() {
		return destMobile;
	}
	public void setDestMobile( String destMobile ) {
		this.destMobile = destMobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate( Date beginDate ) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate( Date endDate ) {
		this.endDate = endDate;
	}
	public Integer getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime( Integer holdingTime ) {
		this.holdingTime = holdingTime;
	}
	public String getSrcMobile() {
		return srcMobile;
	}
	public void setSrcMobile( String srcMobile ) {
		this.srcMobile = srcMobile;
	}
	public Integer getUserCallingDetailId() {
		return userCallingDetailId;
	}
	public void setUserCallingDetailId( Integer userCallingDetailId ) {
		this.userCallingDetailId = userCallingDetailId;
	}
	public String getRecordUrl() {
		return recordUrl;
	}
	public void setRecordUrl( String recordUrl ) {
		this.recordUrl = recordUrl;
	}

}
