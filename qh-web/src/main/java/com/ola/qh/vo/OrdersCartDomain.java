package com.ola.qh.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ola.qh.entity.Orders;

public class OrdersCartDomain{

	private String oid;/////这个订单的ID
	@NotEmpty(message="用户的标识不能为空")
	private String userId;
	@NotNull
	private int ordersType;
	@NotEmpty
	private String address;
	@NotEmpty
	private String receiver;
	@NotEmpty
	private String mobile;
	@Valid
	@NotNull
	@Size(min=1)
	private List<Orders> ordersList = new ArrayList<Orders>();
	
	@NotNull
	private BigDecimal totalPayout;////实际支付多少钱
	
	@NotEmpty(message="支付方式不能为空")
	private String paytypeCode;////支付的类型ALIPAY(支付宝支付)   WXPAY(微信支付)
	@NotEmpty(message="支付名称不能为空")
	private String paytypeName;////支付名称
	
	private String leaveMessage;////留言
	
	private String sex;////性别
	
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
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
