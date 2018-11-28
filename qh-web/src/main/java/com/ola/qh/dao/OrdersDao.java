package com.ola.qh.dao;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayPipeline;

public interface OrdersDao {
	
	public int insertOrders(Orders orders);
	
	public int insertOrdersPayment(OrdersPayment op);
	
	public PayPipeline singlePayPipeline(String code);

}
