package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopDomain;

public interface IShopService {

	public Results<String> shopSaveUpdate(Shop shop);
	
	public List<Shop> selectShopByUserId(String userId);
	
	public List<Shop> listShop(ShopDomain sd);
}
