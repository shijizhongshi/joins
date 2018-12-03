package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Shop;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.Results;

/**
 * 
* @ClassName: ShopController  
* @Description: 店铺信息的保存  
* @author guoyuxue  
* @date 2018年11月22日  
*
 */
@RestController
@RequestMapping("/api/shop")
public class ShopController {

	@Autowired
	private IShopService shopService;
	/**
	 * 
	 * <p>Title: saveUpdateShop</p>  
	 * <p>保存和修改shop</p>  
	 * @param shop
	 * @param valid
	 * @return
	 */
	@RequestMapping(value="/saveUpdate",method=RequestMethod.POST)
	public Results<String> saveUpdateShop(@RequestBody @Valid Shop shop,BindingResult valid){
		
		Results<String> result = new Results<String>();
		if(shop.getId()==null || "".equals(shop.getId())){
			////////保存验证必填信息
			if(valid.hasErrors()){
				result.setStatus("1");
				result.setMessage("店铺信息填写不完整");
				return result;
			}
		}
		result = shopService.shopSaveUpdate(shop);
		return result;
	}
	
	/**
	 * 
	 * <p>Title: selectShopByUserId</p>  
	 * <p>查店铺的基本信息</p>  
	 * @param userId
	 * @return
	 */
	@RequestMapping("/selectByUserId")
	public Results<List<Shop>> selectShopByUserId(@RequestParam(name="userId",required=true)String userId){
		Results<List<Shop>> result= new Results<List<Shop>>();
		List<Shop> shopList = shopService.selectShopByUserId(userId);
		result.setData(shopList);
		result.setStatus("0");
		result.setCount(shopList.size());//////如果他等于2的话说明两种店铺类型都有
		return result;
	}
	
}
