package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.ShopDrug;

public class ShopDrugVo{

	private String shopName;
	
	private String shopId;
	
	private String shopAddress;
	
	private String shopLogo;
	
	private String shopDoorUrl;

	private List<ShopDrug> salesList=new ArrayList<ShopDrug>();///店家自己促销的集合
	
	private List<ShopDrug> list=new ArrayList<ShopDrug>();/////店家商品所有的集合
	

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopDoorUrl() {
		return shopDoorUrl;
	}

	public void setShopDoorUrl(String shopDoorUrl) {
		this.shopDoorUrl = shopDoorUrl;
	}

	public List<ShopDrug> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<ShopDrug> salesList) {
		this.salesList = salesList;
	}

	public List<ShopDrug> getList() {
		return list;
	}

	public void setList(List<ShopDrug> list) {
		this.list = list;
	}


	
	
	
}
