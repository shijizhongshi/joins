package com.ola.qh.entity;

import java.util.Date;

public class UserIntomoneyHistory {

	private String id;
	
	private String userId;
	
	private int money;
	
	private int intoStatus;
	
	private Date addtime;
	
	private Date updatetime;
	
	private String orderid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
