package com.zaijiadd.app.applyflow.entity;

import java.sql.Timestamp;

public class StoreInfo {
	
    private Long storeId;

    private Integer capital;
    
    private String capitalName;

    private Integer city;
    
    private String cityName;

    private Integer district;
    
    private String districtName;

    private Long applyStoreId;

    private String applyName;

    private String personNum;

    private String phone;

    private Integer street;
    
    private String streetName;
    
	private String detailAddress;

    private String village;

    private Integer status;

    private String addressAuditOpinion;
    
    private String imgsAuditOpinion;
    
    private Integer imgsAuditStatus;
    
    private Integer addressAuditStatus;
    
    private Integer applicant;
    
    private Timestamp applicantTime;
    
    private Integer addressApprover;
    
    private Integer imgsApprover;
    
    private Timestamp addressApprovalTime;
    
    private Timestamp imgsApprovalTime;
   

    public Integer getAddressApprover() {
		return addressApprover;
	}

	public void setAddressApprover(Integer addressApprover) {
		this.addressApprover = addressApprover;
	}

	public Integer getImgsApprover() {
		return imgsApprover;
	}

	public void setImgsApprover(Integer imgsApprover) {
		this.imgsApprover = imgsApprover;
	}

	public Timestamp getAddressApprovalTime() {
		return addressApprovalTime;
	}

	public void setAddressApprovalTime(Timestamp addressApprovalTime) {
		this.addressApprovalTime = addressApprovalTime;
	}

	public Timestamp getImgsApprovalTime() {
		return imgsApprovalTime;
	}

	public void setImgsApprovalTime(Timestamp imgsApprovalTime) {
		this.imgsApprovalTime = imgsApprovalTime;
	}

	public Integer getApplicant() {
		return applicant;
	}

	public void setApplicant(Integer applicant) {
		this.applicant = applicant;
	}

	public Timestamp getApplicantTime() {
		return applicantTime;
	}

	public void setApplicantTime(Timestamp applicantTime) {
		this.applicantTime = applicantTime;
	}

	public Integer getImgsAuditStatus() {
		return imgsAuditStatus;
	}

	public void setImgsAuditStatus(Integer imgsAuditStatus) {
		this.imgsAuditStatus = imgsAuditStatus;
	}

	public Integer getAddressAuditStatus() {
		return addressAuditStatus;
	}

	public void setAddressAuditStatus(Integer addressAuditStatus) {
		this.addressAuditStatus = addressAuditStatus;
	}

	public String getImgsAuditOpinion() {
		return imgsAuditOpinion;
	}

	public void setImgsAuditOpinion(String imgsAuditOpinion) {
		this.imgsAuditOpinion = imgsAuditOpinion;
	}

	public String getAddressAuditOpinion() {
		return addressAuditOpinion;
	}

	public void setAddressAuditOpinion(String addressAuditOpinion) {
		this.addressAuditOpinion = addressAuditOpinion;
	}



	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getCapital() {
		return capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getApplyStoreId() {
		return applyStoreId;
	}

	public void setApplyStoreId(Long applyStoreId) {
		this.applyStoreId = applyStoreId;
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

	public Integer getStreet() {
		return street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}