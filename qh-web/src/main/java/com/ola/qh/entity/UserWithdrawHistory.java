package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class UserWithdrawHistory {

	@NotEmpty(message="ID不能为空")
	private String id;
	@NotEmpty(message="用户ID不能为空")
	private String userId;
	
	private String withdrawTypes;
	
	private double money;
	
	private int payStatus;
	
	private String payMessage;
	
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

	public String getWithdrawTypes() {
		return withdrawTypes;
	}

	public void setWithdrawTypes(String withdrawTypes) {
		this.withdrawTypes = withdrawTypes;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMessage() {
		return payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
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
