package com.zaijiadd.app.product.dto;

import java.math.BigDecimal;

public class ProductGoodsDTO {
	
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;
	private String goodsId;
	private String goodsName;
	private Integer count;
	private BigDecimal unitPrice;
	private BigDecimal totalAmount;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId( Integer categoryId ) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode( String categoryCode ) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName( String categoryName ) {
		this.categoryName = categoryName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId( String goodsId ) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount( Integer count ) {
		this.count = count;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice( BigDecimal unitPrice ) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount( BigDecimal totalAmount ) {
		this.totalAmount = totalAmount;
	}
	
}
