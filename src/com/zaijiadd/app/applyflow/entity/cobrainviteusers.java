package com.zaijiadd.app.applyflow.entity;

import java.util.Date;

public class cobrainviteusers {
    private Integer inviteuserId;

    private String inviteuserName;

    private String inviteuserMobile;

    private Date visitTime;

    private String referrer;

    private Integer applyRole;

    private Integer userState;

    private Date createdDate;

    private Date updatedDate;

    private Integer yjsUserId;

    private String personNumber;

    private Integer fuctionSate;

    private String remark;

    public Integer getInviteuserId() {
        return inviteuserId;
    }

    public void setInviteuserId(Integer inviteuserId) {
        this.inviteuserId = inviteuserId;
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

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Integer getApplyRole() {
        return applyRole;
    }

    public void setApplyRole(Integer applyRole) {
        this.applyRole = applyRole;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
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

    public Integer getYjsUserId() {
        return yjsUserId;
    }

    public void setYjsUserId(Integer yjsUserId) {
        this.yjsUserId = yjsUserId;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getFuctionSate() {
        return fuctionSate;
    }

    public void setFuctionSate(Integer fuctionSate) {
        this.fuctionSate = fuctionSate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}