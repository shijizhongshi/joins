package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.ShopDrugCart;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CartVo;

public interface IShopDrugCartService {

	public List<CartVo> selectShopDrugCart(String userId,int page);
	
	public Results<String> insertShopDrugCart(ShopDrugCart shopDrugCart);
	
	public int deleteShopDrugCart(String id,String userId);
	
	public int updateShopDrugCart(int count, String id,Date updatetime);
	
}
