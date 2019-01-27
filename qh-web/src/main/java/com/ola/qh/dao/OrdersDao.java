package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayPipeline;
import com.ola.qh.entity.PayResult;
import com.ola.qh.vo.OrdersDomain;

public interface OrdersDao {
	
	public int insertOrders(Orders orders);
	
	public int updateOrders(
			@Param("oid")String oid,
			@Param("ordersStatus")String ordersStatus,
			@Param("ordersOldStatus")String ordersOldStatus,
			@Param("updatetime")Date updatetime,
			@Param("expressNo")String expressNo,
			@Param("paidtime") Date paidtime,
			@Param("deliveredtime")Date deliveredtime,
			@Param("paytype")String paytype,
			@Param("payname")String payname);
	
	public Orders singleOrders(String orderId);
	
	public int insertOrdersPayment(OrdersPayment op);
	
	public PayPipeline singlePayPipeline(String code);
	
	public List<OrdersPayment> listByExtransno(String extransno);
	
	public int insertPayResult(PayResult pr);

	public int updateOrdersPayment(
			@Param("id")String id,
			@Param("done")int done,
			@Param("updatetime")Date updatetime,
			@Param("paytypeCode")String paytypeCode,
			@Param("paytypeName")String paytypeName);
	
	public OrdersPayment singlePayment(String ordersId);
	
	public List<Orders> ordersList(OrdersDomain od);
	
	public int ordersListCount(
			@Param("muserId")String muserId,
			@Param("userId")String userId,
			@Param("ordersStatus")String ordersStatus);
	
	public int updateQrcode(
			@Param("id")String id,
			@Param("qrcode")String qrcode);
	
	public int deleteOrders(@Param("id")String id);
}
