package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersDomain;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.util.Results;
import com.ola.qh.vo.OrdersVo;

public interface IOrdersService {

	public Results<List<OrdersPayment>> submitOrders(OrdersVo ordersVo);
	
	public Results<String> updateOrders(String ordersStatus,String ordersName,String expressNo,String ordersId);
	
	public Results<OrdersDomain> singleOrders(String ordersId);
	
	public List<OrdersDomain> listOrders(String statusCode,int pageNo,int pageSize);
}
