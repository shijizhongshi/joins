package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.util.Results;

public interface IOrdersService {

	public Results<List<OrdersPayment>> submitOrders(OrdersVo ordersVo);
	
	public Results<String> updateOrders(String ordersStatus,String ordersName,String expressNo,String ordersId);
	
	public Results<Orders> singleOrders(String ordersId);
	
	public List<Orders> listOrders(int pageNo,int pageSize);
}
