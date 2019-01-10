package com.ola.qh.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class ProductBuyDomain {
	
	@NotEmpty(message="用户的标识不能为空")
	private String userId;
	
	@NotNull(message="购买的类型不能为空")
	private int ordersType;////1:购买课程的    2:预定服务的
	
	@NotEmpty(message="商家的id不能为空")
	private String muserId;
	
	@NotEmpty(message="支付方式不能为空")
	private String payTypeCode;////支付方式
	@NotEmpty(message="支付方式的名称不能为空")
	private String payTypeName;////支付方式的名称
	@NotNull
	private BigDecimal totalPayout;/////实际支付金额
	@NotEmpty(message="产品的标识不能为空")
	private String productId;/////购买的产品(课程或者是服务)不能为空
	@NotNull
	private int count;////对于服务店铺的项目就不止一个了
	
	
	private String mobile;
	
	private String receiver;
	
	private String leaveMessage;
	
	private String sex;
	
	private String paymentType;////团购还是预定
	
	private String presetTime;////预定的时间
	
	private int classStatus;/////1:表示的是全套的课程 0:不处理
	
	
	public int getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}

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

	public String getMuserId() {
		return muserId;
	}

	public void setMuserId(String muserId) {
		this.muserId = muserId;
	}

	public String getPayTypeCode() {
		return payTypeCode;
	}

	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public BigDecimal getTotalPayout() {
		return totalPayout;
	}

	public void setTotalPayout(BigDecimal totalPayout) {
		this.totalPayout = totalPayout;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPresetTime() {
		return presetTime;
	}

	public void setPresetTime(String presetTime) {
		this.presetTime = presetTime;
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

	
	

}
