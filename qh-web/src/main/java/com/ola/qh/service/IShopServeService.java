package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopServeDomain;
import com.ola.qh.vo.ShopServeVo;

public interface IShopServeService {

	public Results<String> saveShopServe(ShopServe ss);
	
	public Results<String> updateShopServe(ShopServe ss);
	
	public Results<ShopServeVo> singleShopServe(String id);
	
	public Results<List<ShopServe>> selectServeList(ShopServeDomain sdd);
	
	public int selectListCount(String shopId,int serveStatus);
	
	
}
