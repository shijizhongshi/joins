package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserIntomoneyHistory {

	private String id;
	
	private String userId;
	
	private BigDecimal money;
	
	private int intoStatus;
	
	private Date addtime;
	
	private Date updatetime;
	
	private String orderId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIntoStatus() {
		return intoStatus;
	}

	public void setIntoStatus(int intoStatus) {
		this.intoStatus = intoStatus;
	}
	
	
	
}
