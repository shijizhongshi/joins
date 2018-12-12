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
	
	private String shopLogo;////商家的头像////购物订单的展示
	
	private String shopName;/////商家的店名//购物订单的展示
	
	private String address;////收货地址店铺订单展示的
	
	private String mobile;/////
	
	private String receiver;////
	
	private int count;////总共多少件商品
	
	private String showtime;////时间的展示
	
	private BigDecimal freight;///运费

	
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
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

	
	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	
	
	
}
