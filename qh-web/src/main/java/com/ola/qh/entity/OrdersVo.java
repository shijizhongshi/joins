package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class OrdersVo{

	@NotEmpty(message="用户的的标识不能为空")
	private String userIds;
	@NotNull(message="订单的类型不能为空")
	private int ordersTypes;
	
	private String address;
	
	private String receiver;
	
	private String mobile;
	@Valid
	@NotNull
	@Size(min=1)
	private List<Orders> ordersList = new ArrayList<Orders>();
	
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public int getOrdersTypes() {
		return ordersTypes;
	}
	public void setOrdersTypes(int ordersTypes) {
		this.ordersTypes = ordersTypes;
	}
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
