package com.ola.qh.vo;

public class OrdersCountVo {

	private int newCount;////代付款的个数
	
	private int paidCount;////待发货的个数
	
	private int deliveredCount;////已发货的订单的个数
	
	private int refundCount;////退款订单的个数

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
