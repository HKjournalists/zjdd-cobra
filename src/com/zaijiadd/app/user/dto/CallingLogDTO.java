package com.zaijiadd.app.user.dto;

import java.util.Date;

public class CallingLogDTO {
	
	private String realname;
	private Date beginDate;
	private Integer holdingTime;
	private String recordUrl;
	
	public String getRealname() {
		return realname;
	}
	public void setRealname( String realname ) {
		this.realname = realname;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate( Date beginDate ) {
		this.beginDate = beginDate;
	}
	public Integer getHoldingTime() {
		return holdingTime;
	}
	public void setHoldingTime( Integer holdingTime ) {
		this.holdingTime = holdingTime;
	}
	public String getRecordUrl() {
		return recordUrl;
	}
	public void setRecordUrl( String recordUrl ) {
		this.recordUrl = recordUrl;
	}

}
