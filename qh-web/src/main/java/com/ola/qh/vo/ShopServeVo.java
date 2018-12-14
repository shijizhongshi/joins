package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.ShopServe;

public class ShopServeVo extends ShopServe{

	private int buyCount;////购买的个数
	
	private List<ShopServe> listserve=new ArrayList<ShopServe>();
	
	private String shopName;///点名
	
	private String address;//商家地址
	
	private double avgGrade;///评分
	
	private String shopMobile;///商家电话

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public List<ShopServe> getListserve() {
		return listserve;
	}

	public void setListserve(List<ShopServe> listserve) {
		this.listserve = listserve;
	}

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


	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}

	public String getShopMobile() {
		return shopMobile;
	}

	public void setShopMobile(String shopMobile) {
		this.shopMobile = shopMobile;
	}
	
	

}
