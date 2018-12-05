package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopDrug;
import com.ola.qh.vo.ShopDrugDomain;

public interface ShopDrugDao {
	
	public int insertDrug(ShopDrug shopDrug);
	
	public int updateDrug(ShopDrug shopDrug);
	
	public ShopDrug selectById(String drugId);
	
	public List<ShopDrug> selectDrugList(ShopDrugDomain sdd);
	
	public int selectDrugListCount(ShopDrugDomain sdd);
	
	public int updateStocks(int stocks,String id);

}
