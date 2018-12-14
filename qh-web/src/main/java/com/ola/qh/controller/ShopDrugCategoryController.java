package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.service.IShopDrugCategoryService;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName: ShopDrugCategoryController
 * @Description: 药品分类的查询
 * @author guozihan
 * @date 2018/12/7
 *
 */
@RestController
@RequestMapping(value = "/api/shopdrugcategory")
public class ShopDrugCategoryController {

	@Autowired
	private IShopDrugCategoryService shopDrugCategoryService;
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Results<List<ShopDrugCategory>> selectShopDrugCategory() {

		Results<List<ShopDrugCategory>> results = new Results<List<ShopDrugCategory>>();

		List<ShopDrugCategory> list = shopDrugCategoryService.selectShopDrugCategory();

		results.setData(list);
		results.setStatus("0");
		return results;

	}
	
	
	
	@RequestMapping(value = "/sublist", method = RequestMethod.GET)
	public Results<List<ShopDrugCategory>> selectShopDrugSubcategory(String categoryId) {

		Results<List<ShopDrugCategory>> results = new Results<List<ShopDrugCategory>>();

		List<ShopDrugCategory> list = shopDrugCategoryService.selectShopDrugSubcategory(categoryId);

		results.setData(list);
		results.setStatus("0");
		return results;

	}

}
