package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.util.Results;

public interface IOrdersService {

	public Results<List<OrdersPayment>> submitOrders(OrdersVo ordersVo);
	
}
