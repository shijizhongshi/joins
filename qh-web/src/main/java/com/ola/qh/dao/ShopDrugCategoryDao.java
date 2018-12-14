package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrugCategory;

public interface ShopDrugCategoryDao {

	public List<ShopDrugCategory> drugCategoryList();
	

	public List<ShopDrugCategory> drugSubcategoryList(@Param("categoryId")String categoryId);
	
	public List<ShopDrugCategory> drugSubsubcategoryList(@Param("categoryId")String categoryId);
}
