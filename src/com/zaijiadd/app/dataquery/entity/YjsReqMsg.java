package com.zaijiadd.app.dataquery.entity;

import java.util.Date;

public class YjsReqMsg {
    private Integer id;

    private String cussrc;

    private String srcdtl;

    private Date protim;

    private Date lastphntim;

    private Integer phncnt;

    private String name;

    private String phone;

    private String email;

    private String wx;

    private String qq;

    private String city;

    private String custype;

    private String cgroup;

    private String cuser;

    private String txnsts;

    private String salman;

    private String remark;

    private String rsv1;

    private String rsv2;

    private String rsv3;
    
    private Integer statusType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCussrc() {
        return cussrc;
    }

    public void setCussrc(String cussrc) {
        this.cussrc = cussrc == null ? null : cussrc.trim();
    }

    public String getSrcdtl() {
        return srcdtl;
    }

    public void setSrcdtl(String srcdtl) {
        this.srcdtl = srcdtl == null ? null : srcdtl.trim();
    }

    public Date getProtim() {
        return protim;
    }

    public void setProtim(Date protim) {
        this.protim = protim;
    }

    public Date getLastphntim() {
        return lastphntim;
    }

    public void setLastphntim(Date lastphntim) {
        this.lastphntim = lastphntim;
    }

    public Integer getPhncnt() {
        return phncnt;
    }

    public void setPhncnt(Integer phncnt) {
        this.phncnt = phncnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCustype() {
        return custype;
    }

    public void setCustype(String custype) {
        this.custype = custype == null ? null : custype.trim();
    }

    public String getCgroup() {
        return cgroup;
    }

    public void setCgroup(String cgroup) {
        this.cgroup = cgroup == null ? null : cgroup.trim();
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser == null ? null : cuser.trim();
    }

    public String getTxnsts() {
        return txnsts;
    }

    public void setTxnsts(String txnsts) {
        this.txnsts = txnsts == null ? null : txnsts.trim();
    }

    public String getSalman() {
        return salman;
    }

    public void setSalman(String salman) {
        this.salman = salman == null ? null : salman.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRsv1() {
        return rsv1;
    }

    public void setRsv1(String rsv1) {
        this.rsv1 = rsv1 == null ? null : rsv1.trim();
    }

    public String getRsv2() {
        return rsv2;
    }

    public void setRsv2(String rsv2) {
        this.rsv2 = rsv2 == null ? null : rsv2.trim();
    }

    public String getRsv3() {
        return rsv3;
    }

    public void setRsv3(String rsv3) {
        this.rsv3 = rsv3 == null ? null : rsv3.trim();
    }

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType( Integer statusType ) {
		this.statusType = statusType;
	}
}