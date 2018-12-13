package com.ola.qh.vo;

public class ShopCountVo {

	private int salesCount;////出售个数
	
	private int downCount;///下架的个数
	
	private int wlimitCount;///未审核的个数

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getDownCount() {
		return downCount;
	}

	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}

	public int getWlimitCount() {
		return wlimitCount;
	}

	public void setWlimitCount(int wlimitCount) {
		this.wlimitCount = wlimitCount;
	}
	
	
}
