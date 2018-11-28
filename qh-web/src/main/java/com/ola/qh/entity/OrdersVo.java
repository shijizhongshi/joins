package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class OrdersVo{

	private String userId;
	private int ordersType;
	
	private String address;
	
	private String receiver;
	
	private String mobile;
	@Valid
	@NotNull
	@Size(min=1)
	private List<Orders> ordersList = new ArrayList<Orders>();
	
	private BigDecimal totalPayout;////实际支付多少钱
	
	private String paytypeCode;////支付的类型ALIPAY(支付宝支付)   WXPAY(微信支付)
	
	private String paytypeName;////支付名称
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOrdersType() {
		return ordersType;
	}
	public void setOrdersType(int ordersType) {
		this.ordersType = ordersType;
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
	public BigDecimal getTotalPayout() {
		return totalPayout;
	}
	public void setTotalPayout(BigDecimal totalPayout) {
		this.totalPayout = totalPayout;
	}
	public String getPaytypeCode() {
		return paytypeCode;
	}
	public void setPaytypeCode(String paytypeCode) {
		this.paytypeCode = paytypeCode;
	}
	public String getPaytypeName() {
		return paytypeName;
	}
	public void setPaytypeName(String paytypeName) {
		this.paytypeName = paytypeName;
	}
	
	
}
