package com.zaijiadd.app.applyflow.dto;

public class StoreAddressDTO {

	private String applyName;

	private String personNum;

	private String phone;

	private String detailAddress;

	private Integer addressAuditStatus;

	private Long storeId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Integer getAddressAuditStatus() {
		return addressAuditStatus;
	}

	public void setAddressAuditStatus(Integer addressAuditStatus) {
		this.addressAuditStatus = addressAuditStatus;
	}

}