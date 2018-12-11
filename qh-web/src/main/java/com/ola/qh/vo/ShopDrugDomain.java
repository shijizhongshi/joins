package com.ola.qh.vo;

public class ShopDrugDomain {

	private int pageNo;
	
	private int pageSize;
	
	private String shopId;
	
	private int ishot;
	
	private int status;/////如果等于的1的时候表示的是访问的商品店铺的首页 2:下架的商品  
	
	private String drugName;
	
	private String categoryName;
	
	private String categorySubname;
	
	private int isrecommend;
	
	private int issales;
	
	private int ordersSales;///按照销量排序
	
	private int ordersstocks;////按照库存排序
	
	private int orderstime;////按照时间排序
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public int getIshot() {
		return ishot;
	}

	public void setIshot(int ishot) {
		this.ishot = ishot;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategorySubname() {
		return categorySubname;
	}

	public void setCategorySubname(String categorySubname) {
		this.categorySubname = categorySubname;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

	public int getIssales() {
		return issales;
	}

	public void setIssales(int issales) {
		this.issales = issales;
	}

	public int getOrdersSales() {
		return ordersSales;
	}

	public void setOrdersSales(int ordersSales) {
		this.ordersSales = ordersSales;
	}

	public int getOrdersstocks() {
		return ordersstocks;
	}

	public void setOrdersstocks(int ordersstocks) {
		this.ordersstocks = ordersstocks;
	}

	public int getOrderstime() {
		return orderstime;
	}

	public void setOrderstime(int orderstime) {
		this.orderstime = orderstime;
	}

}
