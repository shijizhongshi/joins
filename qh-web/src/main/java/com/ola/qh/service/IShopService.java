package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;

public interface IShopService {

	public Results<String> shopSaveUpdate(Shop shop);
	
	public List<Shop> selectShopByUserId(String userId);
}
