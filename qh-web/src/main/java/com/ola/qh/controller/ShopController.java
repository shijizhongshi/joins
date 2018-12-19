package com.ola.qh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopVo;

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
			
			if(shop.getIdcard()!=null && !"".equals(shop.getIdcard())){
				Pattern p=Pattern.compile("(^[1-8][0-7]{2}\\d{3}([12]\\d{3})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx])$)");
				if(!p.matcher(shop.getIdcard()).matches()){
					result.setStatus("1");
					result.setMessage("身份证号格式不准确~");
					return result;
				}
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
		List<Shop> shopList = shopService.selectShopByUserId(userId,null,0);
		result.setData(shopList);
		result.setStatus("0");
		result.setCount(shopList.size());//////如果他等于2的话说明两种店铺类型都有
		return result;
	}
	/**
	 * 一般都是服务店铺的查询
	 * <p>Title: listShop</p>  
	 * <p>Description: </p>  
	 * @param page
	 * @param shopName
	 * @param address
	 * @param shopType
	 * @return
	 */
	@RequestMapping("/list")
	public Results<List<Shop>> listShop(
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="shopName",required=false)String shopName,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="serveDomain",required=false)String serveDomain,
			@RequestParam(name="shopType",required=true)int shopType){
		
		
		Results<List<Shop>> result = new Results<List<Shop>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		ShopDomain sd=new ShopDomain();
		sd.setAddress(address);
		sd.setPageNo(pageNo);
		sd.setPageSize(pageSize);
		sd.setServeDomain(serveDomain);
		sd.setShopName(shopName);
		sd.setShopType(shopType);
		
		List<Shop> listShop = shopService.listShop(sd);
		result.setData(listShop);
		result.setStatus("0");
		return result;
		
	}	
	
	@RequestMapping("/single")
	public Results<ShopVo> singleShop(@RequestParam(name="shopId",required=true)String shopId){
		
		return shopService.singleShop(shopId);
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<String>> selectShopServeType() {

		Results<List<String>> results = new Results<List<String>>();
		List<Shop> list = shopService.selectShopServeType();
		if (list != null && list.size() != 0) {
			List<String> List = new ArrayList<String>();
			for(int i = 0;i < list.size();i++){
				Shop shop = list.get(i);
			    String typeName=shop.getServetypeName();
			    List.add(typeName);
			}
			results.setData(List);
			results.setStatus("0");
			return results;
		}
		results.setStatus("1");
		return results;
	}
}
