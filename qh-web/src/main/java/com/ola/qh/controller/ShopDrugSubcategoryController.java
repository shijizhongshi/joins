package com.ola.qh.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrugSubcategory;
import com.ola.qh.service.IShopDrugSubcategoryService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/shopdrugsubcategory")
public class ShopDrugSubcategoryController {

	@Autowired
	private IShopDrugSubcategoryService shopDrugSubcategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<ShopDrugSubcategory>> selectShopDrugSubcategory(String categoryId){
		
		Results<List<ShopDrugSubcategory>> results=new Results<List<ShopDrugSubcategory>>();
		
		List<ShopDrugSubcategory> list=shopDrugSubcategoryService.selectShopDrugSubcategory(categoryId);
		
		if(list==null || list.size()==0){
			
			results.setMessage("未添加子分类");
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
}
