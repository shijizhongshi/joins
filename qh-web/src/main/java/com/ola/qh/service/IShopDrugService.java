package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.util.Results;

public interface IShopDrugService {

	public Results<String> insertDrug(ShopDrug shopDrug);
	
	public Results<String> updateDrug(ShopDrug shopDrug);
	
	public Results<ShopDrug> selectById(String drugId);
	
	public Results<List<ShopDrug>> selectDrugList(String shopId,int pageNo,int pageSize,int ishot);
}
