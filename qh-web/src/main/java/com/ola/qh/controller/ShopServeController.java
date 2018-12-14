package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.service.IShopServeService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopServeDomain;
import com.ola.qh.vo.ShopServeVo;
/**
 * 服务项目的操作增删改查
* @ClassName: ShopServeController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年12月3日  
*
 */
@RestController
@RequestMapping("/api/shopserve")
public class ShopServeController {

	@Autowired
	private IShopServeService shopServeService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> saveShopServe(@RequestBody @Valid ShopServe shopServe,BindingResult valid){
		Results<String> result=new Results<String>();
		if(valid.hasErrors()){
			result.setStatus("1");
			result.setMessage("服务项目不完整,请检查");
			return result;
		}
		return shopServeService.saveShopServe(shopServe);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateShopServe(@RequestBody ShopServe ss){
		Results<String> result = new Results<String>();
		if(ss.getId()==null || "".equals(ss.getId())){
			result.setStatus("1");
			result.setMessage("项目的标识不能为空");
			return result;
		}
		return shopServeService.updateShopServe(ss);
		
	}
	
	@RequestMapping("/single")
	public Results<ShopServeVo> singleServe(@RequestParam(name="id",required=true)String id){
		return shopServeService.singleShopServe(id);
	}
	
	@RequestMapping("/list")
	public Results<List<ShopServe>> listServe(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="shopId",required=false)String shopId,
			@RequestParam(name="serveId",required=false)String serveId){
		
		Results<List<ShopServe>> result=new Results<List<ShopServe>>();
		int pageSize = Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		ShopServeDomain ssd=new ShopServeDomain();
		ssd.setPageNo(pageNo);
		ssd.setPageSize(pageSize);
		ssd.setShopId(shopId);
		ssd.setId(serveId);
		result=shopServeService.selectServeList(ssd);
		return result;
	}
	
	
	
}
