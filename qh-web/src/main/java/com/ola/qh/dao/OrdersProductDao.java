package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.vo.OrdersProductDomain;

public interface OrdersProductDao {

	public int insertOrdersProduct(OrdersProduct op);
	
	public List<OrdersProduct> selectByOid(@Param("orderId")String orderId,@Param("statusCode")String statusCode);
	
	public int updateOrdersProduct(
			@Param("id")String id,
			@Param("statusCode")String statusCode,
			@Param("statusName")String statusName,
			@Param("oldStatusCode")String oldStatusCode,
			@Param("updatetime")Date updatetime);
	
	public OrdersProduct singleOrdersProduct(String id);
	
	public List<OrdersProduct> listOrdersProduct(OrdersProductDomain opd);
	
	public int listOrdersProductCount(@Param("muserId")String muserId,@Param("userId")String userId);
	
	public int ordersCount(String productId);
}
