package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrdersDomain {

    private String id;
	
	private String userId;
	
	private String muserId;////
	
	private String orderno;
	
	private int ordersType;////0:药品订单  1:课程订单
	
	private BigDecimal payaccount;/////实际支付金额
	
	private String ordersStatus;/////订单的状态
	
	private String expressNo;////快递单号
	
	private String deliveredtime;///发货时间
	
	private String paidtime;/////支付时间
	
	private List<OrdersProduct> product = new ArrayList<OrdersProduct>();

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

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(int ordersType) {
		this.ordersType = ordersType;
	}

	public BigDecimal getPayaccount() {
		return payaccount;
	}

	public void setPayaccount(BigDecimal payaccount) {
		this.payaccount = payaccount;
	}

	public String getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getDeliveredtime() {
		return deliveredtime;
	}

	public void setDeliveredtime(String deliveredtime) {
		this.deliveredtime = deliveredtime;
	}

	public String getPaidtime() {
		return paidtime;
	}

	public void setPaidtime(String paidtime) {
		this.paidtime = paidtime;
	}

	public List<OrdersProduct> getProduct() {
		return product;
	}

	public void setProduct(List<OrdersProduct> product) {
		this.product = product;
	}
	
	
	
}
