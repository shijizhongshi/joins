package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.vo.ShopDomain;

public interface ShopDao {

	public int insertShop(Shop shop);
	
	public int updateShop(Shop shop);
	
	public List<Shop> selectShopByUserId(
			@Param("userId")String userId,
			@Param("shopId")String shopId,
			@Param("shopType")int shopType);
	
	public List<Shop> listShop(ShopDomain sd);
	
	public double commentGrade(String shopId);
	
	public int insertImg(ShopImg img);
	
	public int deleteImg(String id);
	
	public List<ShopImg> selectList(@Param("shopId")String shopId,@Param("subtype")int subtype);
}
