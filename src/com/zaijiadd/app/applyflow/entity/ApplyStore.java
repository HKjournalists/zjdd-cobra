package com.zaijiadd.app.applyflow.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ApplyStore {

	private Integer applyStoreId;

	private Integer applyType;

	private Integer applyPersonType;

	private String capital;

	private String city;

	private String district;

	private Integer agencyType;

	private Integer agencyTime;

	private Integer dealershipNum;

	private String applyName;

	private String personNum;

	private String phone;

	private String postAddr;

	private String maiAddr;

	private String urgencyPerson;

	private String urgencyPhone;

	private String companyname;

	private Integer payWay;

	private Integer paymoneyType;

	private BigDecimal paidMoney;

	private BigDecimal needPaymoney;

	private Integer yjsUserId;

	private Integer financeCheck;

	private Integer managersCheck;

	private Integer applyStatus;

	private Integer approveState;

	private Integer whoCheck;

	private Integer storeNumm;

	private String bankNumone;

	private String bankNumtwo;

	private Integer capitalId;

	private Integer cityId;

	private Integer districtId;

	private Date createdDate;

	private Date updatedDate;

	private Integer whetherSelf;

	private Integer payBank;

	private String paySubbranchBank;

	private String payPersonName;

	private String payAlipayNum;

	private String possNum;

	private Integer whetherStartApply;

	private String payBankName;
	private Integer roleApprove;

	private BigDecimal contractAmount;
	private String payBankCode;

	public Integer getRoleApprove() {
		return roleApprove;
	}

	public void setRoleApprove(Integer roleApprove) {
		this.roleApprove = roleApprove;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public Integer getApplyStoreId() {
		return applyStoreId;
	}

	public void setApplyStoreId(Integer applyStoreId) {
		this.applyStoreId = applyStoreId;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Integer getApplyPersonType() {
		return applyPersonType;
	}

	public void setApplyPersonType(Integer applyPersonType) {
		this.applyPersonType = applyPersonType;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getAgencyType() {
		return agencyType;
	}

	public void setAgencyType(Integer agencyType) {
		this.agencyType = agencyType;
	}

	public Integer getAgencyTime() {
		return agencyTime;
	}

	public void setAgencyTime(Integer agencyTime) {
		this.agencyTime = agencyTime;
	}

	public Integer getDealershipNum() {
		return dealershipNum;
	}

	public void setDealershipNum(Integer dealershipNum) {
		this.dealershipNum = dealershipNum;
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

	public String getPostAddr() {
		return postAddr;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	public String getMaiAddr() {
		return maiAddr;
	}

	public void setMaiAddr(String maiAddr) {
		this.maiAddr = maiAddr;
	}

	public String getUrgencyPerson() {
		return urgencyPerson;
	}

	public void setUrgencyPerson(String urgencyPerson) {
		this.urgencyPerson = urgencyPerson;
	}

	public String getUrgencyPhone() {
		return urgencyPhone;
	}

	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Integer getPaymoneyType() {
		return paymoneyType;
	}

	public void setPaymoneyType(Integer paymoneyType) {
		this.paymoneyType = paymoneyType;
	}

	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	public BigDecimal getNeedPaymoney() {
		return needPaymoney;
	}

	public void setNeedPaymoney(BigDecimal needPaymoney) {
		this.needPaymoney = needPaymoney;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Integer getYjsUserId() {
		return yjsUserId;
	}

	public void setYjsUserId(Integer yjsUserId) {
		this.yjsUserId = yjsUserId;
	}

	public Integer getFinanceCheck() {
		return financeCheck;
	}

	public void setFinanceCheck(Integer financeCheck) {
		this.financeCheck = financeCheck;
	}

	public Integer getManagersCheck() {
		return managersCheck;
	}

	public void setManagersCheck(Integer managersCheck) {
		this.managersCheck = managersCheck;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Integer getApproveState() {
		return approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	public Integer getWhoCheck() {
		return whoCheck;
	}

	public void setWhoCheck(Integer whoCheck) {
		this.whoCheck = whoCheck;
	}

	public Integer getStoreNumm() {
		return storeNumm;
	}

	public void setStoreNumm(Integer storeNumm) {
		this.storeNumm = storeNumm;
	}

	public String getBankNumone() {
		return bankNumone;
	}

	public void setBankNumone(String bankNumone) {
		this.bankNumone = bankNumone;
	}

	public String getBankNumtwo() {
		return bankNumtwo;
	}

	public void setBankNumtwo(String bankNumtwo) {
		this.bankNumtwo = bankNumtwo;
	}

	public Integer getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(Integer capitalId) {
		this.capitalId = capitalId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getWhetherSelf() {
		return whetherSelf;
	}

	public void setWhetherSelf(Integer whetherSelf) {
		this.whetherSelf = whetherSelf;
	}

	public Integer getPayBank() {
		return payBank;
	}

	public void setPayBank(Integer payBank) {
		this.payBank = payBank;
	}

	public String getPaySubbranchBank() {
		return paySubbranchBank;
	}

	public void setPaySubbranchBank(String paySubbranchBank) {
		this.paySubbranchBank = paySubbranchBank;
	}

	public String getPayPersonName() {
		return payPersonName;
	}

	public void setPayPersonName(String payPersonName) {
		this.payPersonName = payPersonName;
	}

	public String getPayAlipayNum() {
		return payAlipayNum;
	}

	public void setPayAlipayNum(String payAlipayNum) {
		this.payAlipayNum = payAlipayNum;
	}

	public String getPossNum() {
		return possNum;
	}

	public void setPossNum(String possNum) {
		this.possNum = possNum;
	}

	public Integer getWhetherStartApply() {
		return whetherStartApply;
	}

	public void setWhetherStartApply(Integer whetherStartApply) {
		this.whetherStartApply = whetherStartApply;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

}
