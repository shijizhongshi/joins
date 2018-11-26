package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;

public interface ShopDrugDao {
	
	public int insertDrug(ShopDrug shopDrug);
	
	public int updateDrug(ShopDrug shopDrug);
	
	public ShopDrug selectById(String drugId);
	
	public List<ShopDrug> selectDrugList(
			@Param("shopId") String shopId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);

}
