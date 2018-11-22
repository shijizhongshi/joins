package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.Shop;

public interface ShopDao {

	public int insertShop(Shop shop);
	
	public int updateShop(Shop shop);
	
	public List<Shop> selectShopByUserId(String userId);
}
