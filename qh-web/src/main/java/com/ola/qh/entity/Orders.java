package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Orders {

	private String id;
	
	private String userId;
	@NotEmpty(message="商家的标识不能为空")
	private String muserId;////
	
	private String orderno;
	
	private int ordersType;////0:药品订单  1:课程订单
	
	private BigDecimal payaccount;/////实际支付金额
	
	private String ordersStatus;/////订单的状态
	
	private String ordersOldStatus;/////老的订单状态
	
	private String address;
	
	private String receiver;
	
	private String mobile;
	
	private String expressNo;////快递单号
	
	private String paytype;///支付方式
	
	private String payname;////支付方式名称
	
	private Date addtime;
	
	private Date updatetime;
	
	private Date deliveredtime;
	
	private Date paidtime;
	@Valid
	@NotNull
	@Size(min=1)
	private List<OrdersProduct> product = new ArrayList<OrdersProduct>();
	
	private String headImgUrl;////商家的头像
	
	private String nickname;/////商家的昵称
	
	private int count;////总共多少件商品

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getOrdersOldStatus() {
		return ordersOldStatus;
	}

	public void setOrdersOldStatus(String ordersOldStatus) {
		this.ordersOldStatus = ordersOldStatus;
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

	public List<OrdersProduct> getProduct() {
		return product;
	}

	public void setProduct(List<OrdersProduct> product) {
		this.product = product;
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

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPayname() {
		return payname;
	}

	public void setPayname(String payname) {
		this.payname = payname;
	}

	public Date getDeliveredtime() {
		return deliveredtime;
	}

	public void setDeliveredtime(Date deliveredtime) {
		this.deliveredtime = deliveredtime;
	}

	public Date getPaidtime() {
		return paidtime;
	}

	public void setPaidtime(Date paidtime) {
		this.paidtime = paidtime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
