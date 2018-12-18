package com.ola.qh.vo;


public class ShopServeDomain {

	private String shopId;
	private String id;
	private int pageNo;
	private int pageSize;
	private String serveName;
	private String paymentType;
	private String serveStatus;
	private int ishot;////后台设置可以在首页展示的服务项目
	private String serveContents;
	
	
	public String getServeContents() {
		return serveContents;
	}
	public void setServeContents(String serveContents) {
		this.serveContents = serveContents;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getServeName() {
		return serveName;
	}
	public void setServeName(String serveName) {
		this.serveName = serveName;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getServeStatus() {
		return serveStatus;
	}
	public void setServeStatus(String serveStatus) {
		this.serveStatus = serveStatus;
	}
	
	
}
