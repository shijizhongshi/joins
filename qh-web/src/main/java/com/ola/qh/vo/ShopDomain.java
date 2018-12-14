package com.ola.qh.vo;


public class ShopDomain {

	private String shopName;///店名
	private String address;///地址
	private int pageNo;
	private int pageSize;
	private int isrecommend;///1:推荐
	private int shopType;///店铺的;类型  1;服务店铺   2:商城店铺
	private String serveDomain;////服务领域
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}
	public int getShopType() {
		return shopType;
	}
	public void setShopType(int shopType) {
		this.shopType = shopType;
	}
	public String getServeDomain() {
		return serveDomain;
	}
	public void setServeDomain(String serveDomain) {
		this.serveDomain = serveDomain;
	}
	
	
}
