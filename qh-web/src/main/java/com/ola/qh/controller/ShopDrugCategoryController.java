package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrugCategory;
import com.ola.qh.service.IShopDrugCategoryService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/shopdrugcategory")
public class ShopDrugCategoryController {

	@Autowired
	private IShopDrugCategoryService shopDrugCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<ShopDrugCategory>> selectShopDrugCategory(){
		
		Results<List<ShopDrugCategory>> results=new Results<List<ShopDrugCategory>>();
		
		List<ShopDrugCategory> list=shopDrugCategoryService.selectShopDrugCategory();
		
		if(list==null || list.size()==0){
			
			results.setMessage("未添加药品分类");
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
}
