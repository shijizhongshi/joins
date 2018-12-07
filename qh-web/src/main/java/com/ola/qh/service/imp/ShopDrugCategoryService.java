package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ola.qh.dao.ShopDrugCategoryDao;
import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.service.IShopDrugCategoryService;

@Service
public class ShopDrugCategoryService implements IShopDrugCategoryService{

	@Autowired
	private ShopDrugCategoryDao shopDrugCategoryDao;
	
@Override
	public List<ShopDrugCategory> selectShopDrugCategory() {
		
		return shopDrugCategoryDao.selectShopDrugCategory();
	}



}
