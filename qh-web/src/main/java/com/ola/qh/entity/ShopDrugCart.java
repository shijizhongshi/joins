package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class ShopDrugCart {

	private String id;
	
	@NotEmpty(message="用户ID不能为空")
	private String userId;
	
	@NotEmpty(message="卖家不能为空")
	private String muserId;
	
	@NotEmpty(message="药品ID不能为空")
	private String drugId;
	
	@NotEmpty(message="药品名称不能为空")
	private String drugName;
	
	@NotEmpty(message="药品图片路径不能为空")
	private String drugImgUrl;
	
	@NotNull
	private int count;
	
	@NotNull
	private BigDecimal money;
	
	private Date addtime;
	
	private Date updatetime;
	
	private String shopName;///店铺的名称
	
	private int status;////1;失效
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMuserId() {
		return muserId;
	}

	public void setMuserId(String muserId) {
		this.muserId = muserId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugImgUrl() {
		return drugImgUrl;
	}

	public void setDrugImgUrl(String drugImgUrl) {
		this.drugImgUrl = drugImgUrl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
