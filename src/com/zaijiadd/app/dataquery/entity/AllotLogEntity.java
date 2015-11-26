package com.zaijiadd.app.dataquery.entity;

import java.util.Date;

/**
 * 分配 日志 记录实体�?
 * 
 * @author Chensl
 * 
 * 2015�?11�?26�?-下午3:34:43
 */
public class AllotLogEntity {
	
	private Integer allotId;//主键id
	
	private Integer allotUserId;//分配者id
	
	private Integer msgId;//被分配人员详细信息id(客户id)
	
	private Date allotTime;//分配的时�?
	
	private String comments;//备注
	
	private Integer allotToUserId;//分配给谁
	
	private Integer isGroupLeader;//是不是组�? 0-组长�? 1-非组�?
	
	private Integer allotStatusTypeId;
	
	public AllotLogEntity() {
	}

	public AllotLogEntity(Integer allotId) {
		this.allotId = allotId;
	}

	public Integer getAllotUserId() {
		return allotUserId;
	}

	public void setAllotUserId(Integer allotUserId) {
		this.allotUserId = allotUserId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public Date getAllotTime() {
		return allotTime;
	}

	public void setAllotTime(Date allotTime) {
		this.allotTime = allotTime;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getAllotToUserId() {
		return allotToUserId;
	}

	public void setAllotToUserId(Integer allotToUserId) {
		this.allotToUserId = allotToUserId;
	}

	public Integer getIsGroupLeader() {
		return isGroupLeader;
	}

	public void setIsGroupLeader(Integer isGroupLeader) {
		this.isGroupLeader = isGroupLeader;
	}

	public Integer getAllotId() {
		return allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}

	public Integer getAllotStatusTypeId() {
		return allotStatusTypeId;
	}

	public void setAllotStatusTypeId(Integer allotStatusTypeId) {
		this.allotStatusTypeId = allotStatusTypeId;
	}
}
