package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.util.Results;

public interface IShopServeService {

	public Results<String> saveShopServe(ShopServe ss);
	
	public Results<String> updateShopServe(ShopServe ss);
	
	public Results<ShopServe> singleShopServe(String id);
	
	public Results<List<ShopServe>> selectServeList(
			@Param("shopId")String shopId,
			@Param("id")String id,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
}
