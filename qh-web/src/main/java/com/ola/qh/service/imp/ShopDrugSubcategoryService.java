package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDrugSubcategoryDao;
import com.ola.qh.entity.ShopDrugSubcategory;
import com.ola.qh.service.IShopDrugSubcategoryService;

@Service
public class ShopDrugSubcategoryService implements IShopDrugSubcategoryService{

	@Autowired
	private ShopDrugSubcategoryDao shopDrugSubcategoryDao;

	@Override
	public List<ShopDrugSubcategory> selectShopDrugSubcategory(String categoryId) {
		
		return shopDrugSubcategoryDao.selectShopDrugSubcategory(categoryId);
	}



	
	
	

}
