package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrugSubcategory;

public interface ShopDrugSubcategoryDao {
	
	public List<ShopDrugSubcategory> selectShopDrugSubcategory(@Param("categoryId")String categoryId);
	

}
