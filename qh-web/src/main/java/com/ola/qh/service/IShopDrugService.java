package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopCountVo;
import com.ola.qh.vo.ShopDrugDomain;
import com.ola.qh.vo.ShopDrugVo;

public interface IShopDrugService {

	public Results<String> insertDrug(ShopDrug shopDrug);
	
	public Results<String> updateDrug(ShopDrug shopDrug);
	
	public Results<ShopDrug> selectById(String drugId,String userId);
	
	public Results<ShopDrugVo> selectDrugList(ShopDrugDomain sdd);
	
	public Results<ShopCountVo> shopCount(String shopId);
}
