package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Shop;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopVo;

public interface IShopService {

	public Results<String> shopSaveUpdate(Shop shop);
	
	public List<Shop> selectShopByUserId(String userId,String shopId,int shopType);
	
	public List<Shop> listShop(ShopDomain sd);
	
	public Results<ShopVo> singleShop(String shopId);
	
	public List<Shop> selectShopServeType();
}
