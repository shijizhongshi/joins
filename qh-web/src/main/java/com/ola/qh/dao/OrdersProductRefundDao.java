package com.ola.qh.dao;

import com.ola.qh.entity.OrdersProductRefund;

public interface OrdersProductRefundDao {

	public int insertRefund(OrdersProductRefund or);
	
	public int updateRefund(OrdersProductRefund or);
	
	public OrdersProductRefund singleRefund(String ordersProductId);
}
