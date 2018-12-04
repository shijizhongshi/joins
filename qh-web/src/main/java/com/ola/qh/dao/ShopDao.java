package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;

public interface ShopDao {

	public int insertShop(Shop shop);
	
	public int updateShop(Shop shop);
	
	public List<Shop> selectShopByUserId(
			@Param("userId")String userId,
			@Param("shopId")String shopId);
	
	public int insertImg(ShopImg img);
	
	public int deleteImg(String id);
	
	public List<ShopImg> selectList(String shopId);
}
