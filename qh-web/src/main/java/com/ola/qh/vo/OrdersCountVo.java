package com.ola.qh.vo;

public class OrdersCountVo {

	private int newCount;////代付款的个数
	
	private int paidCount;////待发货的个数
	
	private int wuseCount;/////待使用的个数
	
	private int deliveredCount;////已发货的订单的个数
	
	private int refundCount;////退款订单的个数
	
	private int favoriteCount;///收藏的个数
	
	private int doudouCount;/////豆豆的个数
	

	public int getWuseCount() {
		return wuseCount;
	}

	public void setWuseCount(int wuseCount) {
		this.wuseCount = wuseCount;
	}

	public int getDoudouCount() {
		return doudouCount;
	}

	public void setDoudouCount(int doudouCount) {
		this.doudouCount = doudouCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public int getPaidCount() {
		return paidCount;
	}

	public void setPaidCount(int paidCount) {
		this.paidCount = paidCount;
	}

	public int getDeliveredCount() {
		return deliveredCount;
	}

	public void setDeliveredCount(int deliveredCount) {
		this.deliveredCount = deliveredCount;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}
	
	
	
}
