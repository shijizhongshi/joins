package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.ShopDrugCart;

public interface IShopDrugCartService {

	public List<ShopDrugCart> selectShopDrugCart(String userId,int pageNo,int pageSize);
	
	public int insertShopDrugCart(ShopDrugCart shopDrugCart);
	
	public int deleteShopDrugCart(String id);
	
	public int deleteAllShopDrugCart(String userId);

	public int updateShopDrugCart(int count, String id,Date updatetime);
	
	public ShopDrugCart existShopDrugCart(String drugId,String userId);
}
