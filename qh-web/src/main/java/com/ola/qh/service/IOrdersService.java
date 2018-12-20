package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.util.Results;
import com.ola.qh.vo.OrdersCartDomain;
import com.ola.qh.vo.OrdersCountVo;
import com.ola.qh.vo.OrdersDomain;
import com.ola.qh.vo.OrdersVo;

public interface IOrdersService {

	public Results<List<OrdersPayment>> submitOrders(OrdersCartDomain ordersVo);
	
	public Results<String> updateOrders(String ordersStatus,String ordersName,String expressNo,String ordersId);
	
	public Results<String> updateServe(String statusCode,String ordersId,String userId);
	
	public Results<OrdersVo> singleOrders(String ordersId);
	
	public List<OrdersVo> listOrders(OrdersDomain od);
	
	public Results<OrdersCountVo> countOrders(String muserId,String userId);
	
	
}
