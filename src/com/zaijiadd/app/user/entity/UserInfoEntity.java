package com.zaijiadd.app.user.entity;

public class UserInfoEntity {
	
	private Integer userId;
	private String mobile;
	private String realname;
	private String password;
	private String email;
	private Integer roleId;
	private String isLeader;
	private Integer orgId;
	private Integer landlineId;
	private Integer jobId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname( String realname ) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail( String email ) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId( Integer roleId ) {
		this.roleId = roleId;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader( String isLeader ) {
		this.isLeader = isLeader;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId( Integer orgId ) {
		this.orgId = orgId;
	}
	public Integer getLandlineId() {
		return landlineId;
	}
	public void setLandlineId( Integer landlineId ) {
		this.landlineId = landlineId;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId( Integer jobId ) {
		this.jobId = jobId;
	}

}
