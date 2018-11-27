package com.ola.qh.service;

import com.ola.qh.entity.OrdersVo;
import com.ola.qh.util.Results;

public interface IOrdersService {

	public Results<String> submitOrders(OrdersVo ordersVo);
	
}
