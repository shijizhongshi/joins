package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ShopDrugSubcategory;

public interface IShopDrugSubcategoryService {

	public List<ShopDrugSubcategory> selectShopDrugSubcategory(String categoryId);

}
