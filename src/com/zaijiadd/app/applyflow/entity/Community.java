package com.zaijiadd.app.applyflow.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Community {
    private Integer id;

    private Integer locationId;

    private String name;

    private String addr;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private BigDecimal latitudeMin;

    private BigDecimal latitudeMax;

    private BigDecimal longitudeMin;

    private BigDecimal longitudeMax;

    private Integer couponNum;

    private Integer couponTakenNum;

    private Byte state;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLatitudeMin() {
        return latitudeMin;
    }

    public void setLatitudeMin(BigDecimal latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public BigDecimal getLatitudeMax() {
        return latitudeMax;
    }

    public void setLatitudeMax(BigDecimal latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

    public BigDecimal getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(BigDecimal longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public BigDecimal getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(BigDecimal longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public Integer getCouponTakenNum() {
        return couponTakenNum;
    }

    public void setCouponTakenNum(Integer couponTakenNum) {
        this.couponTakenNum = couponTakenNum;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}