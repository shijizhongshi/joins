package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayPipeline;
import com.ola.qh.entity.PayResult;

public interface OrdersDao {
	
	public int insertOrders(Orders orders);
	
	public int updateOrders(
			@Param("oid")String oid,
			@Param("ordersStatus")String ordersStatus,
			@Param("ordersOldStatus")String ordersOldStatus,
			@Param("updatetime")Date updatetime);
	
	public int insertOrdersPayment(OrdersPayment op);
	
	public PayPipeline singlePayPipeline(String code);
	
	public List<OrdersPayment> listByExtransno(String extransno);
	
	public int insertPayResult(PayResult pr);

}
