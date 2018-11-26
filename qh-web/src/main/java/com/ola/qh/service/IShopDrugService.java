package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.util.Results;

public interface IShopDrugService {

	public Results<String> insertDrug(ShopDrug shopDrug);
	
	public Results<String> updateDrug(ShopDrug shopDrug);
	
	public ShopDrug selectById(String drugId);
	
	public List<ShopDrug> selectDrugList(int pageNo,int pageSize);
}
