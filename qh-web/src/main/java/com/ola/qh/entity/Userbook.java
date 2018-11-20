package com.ola.qh.entity;

import java.util.Date;

public class Userbook {

	private String id;
	
	private String userId;
	
	private int accountMoney;
	
	private Date addtime;
	
	private Date updatetime;

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

	public int getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(int accountMoney) {
		this.accountMoney = accountMoney;
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
	
	
}
