package com.ola.qh.vo;

public class OrdersDomain {

	
	private String ordersStatus;
	private int pageNo;
	private int pageSize;
	private String userId;
	private String muserId;
	private String  ordersType;
	private String ordersno;
	private String expressNo;
	public String getOrdersStatus() {
		return ordersStatus;
	}
	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	
	public String getOrdersType() {
		return ordersType;
	}
	public void setOrdersType(String ordersType) {
		this.ordersType = ordersType;
	}
	public String getOrdersno() {
		return ordersno;
	}
	public void setOrdersno(String ordersno) {
		this.ordersno = ordersno;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	
}
