package com.ola.qh.vo;

public class OrdersProductDomain {

	private String statusCode;
	private int pageNo;
	private int pageSize;
	private String userId;
	private String muserId;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	
	
}
