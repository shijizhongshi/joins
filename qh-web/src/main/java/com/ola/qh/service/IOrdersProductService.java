package com.ola.qh.service;

import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersProductRefund;
import com.ola.qh.util.Results;

public interface IOrdersProductService {

	public Results<String> applyRefund(OrdersProductRefund or);
	
	public Results<String> updateRefund(String ordersProductId,String statusCode);
	
	public Results<String> updateServeRefund(String ordersId,String statusCode);
	
	public Results<String> alterRefund(OrdersProductRefund opr);
	
	public Results<OrdersProduct> single(String ordersProductId);
}
