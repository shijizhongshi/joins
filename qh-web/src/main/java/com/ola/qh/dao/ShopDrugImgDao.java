package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.ShopDrugImg;

public interface ShopDrugImgDao {

	public int insertDrugImg(ShopDrugImg shopDrugImg);
	
	public List<ShopDrugImg> listDrugImg(String drugId);
	
	public int deleteDrugImg(String id);
	
}
