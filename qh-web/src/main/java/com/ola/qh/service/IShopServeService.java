package com.ola.qh.service;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.util.Results;

public interface IShopServeService {

	public Results<String> saveShopServe(ShopServe ss);
	
	public Results<String> updateShopServe(ShopServe ss);
}
