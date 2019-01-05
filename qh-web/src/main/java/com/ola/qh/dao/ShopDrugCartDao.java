package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ola.qh.entity.ShopDrugCart;

public interface ShopDrugCartDao {

	public List<ShopDrugCart> selectShopDrugCart(
			@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int insertShopDrugCart(ShopDrugCart shopDrugCart);
	
	public int updateShopDrugCart(
			@Param("count")int count,
			@Param("id")String id,
			@Param("updatetime")Date updatetime,
			@Param("status")int status,
			@Param("drugId")String drugId);
	
	public int deleteShopDrugCart(@Param("id")String id,@Param("userId")String userId);
	
	public ShopDrugCart singleShopDrugCart(@Param("drugId")String drugId,@Param("userId")String userId);
	
	public List<ShopDrugCart> selectCartList(@Param("drugId")String drugId);
}
