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

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
/**
 *商城店铺的药品的增删改查
* @ClassName: ShopDrugController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月26日  
*
 */
@RestController
@RequestMapping("/api/shopDrug")
public class ShopDrugController {

	@Autowired
	private IShopDrugService shopDrugService;
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> saveShopDrug(@RequestBody @Valid ShopDrug shopDrug,BindingResult vaild){
		Results<String> result = new Results<String>();
		if(vaild.hasErrors()){
			//////说明必填信息不完整
			result.setStatus("1");
			result.setMessage("药品信息填写不完整");
			return result;
		}
		
		result = shopDrugService.insertDrug(shopDrug);
		return result;
	}
	
	/**
	 * 修改药品的信息
	 * <p>Title: updateShopDrug</p>  
	 * <p>Description: </p>  
	 * @param shopDrug
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateShopDrug(@RequestBody ShopDrug shopDrug){
		return shopDrugService.updateDrug(shopDrug);
	}
	/**
	 * 药品的详情页
	 * <p>Title: singleShopDrug</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/single",method=RequestMethod.GET)
	public Results<ShopDrug> singleShopDrug(@RequestParam(name="drugId",required=true) String drugId){
		return shopDrugService.selectById(drugId);
	}
	/**
	 * 药品的集合
	 * <p>Title: listShopDrug</p>  
	 * <p>Description: </p>  
	 * @param page
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<ShopDrug>> listShopDrug(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="shopId",required=false)String shopId,
			@RequestParam(name="ishot",required=false)int ishot){
		
		int pageSize = Patterns.zupageSize;
		int pageNo = (page-1)*pageSize;
		return shopDrugService.selectDrugList(shopId, pageNo, pageSize,ishot);
	}
}
