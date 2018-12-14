package com.ola.qh.dao;

import java.util.List;


import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;
import com.ola.qh.vo.ShopServeDomain;

public interface ShopServeDao {

	public int insertServe(ShopServe ss);
	
	public int updateServe(ShopServe ss);
	
	public ShopServe selectSingle(String id);
	
	public List<ShopServe> selectList(ShopServeDomain ssd);
	
	public int insertServeImg(ShopServeImg ssi);
	
	public int deleteServeImg(String id);
	
	public List<ShopServeImg> selectByServeId(String serveId);
	
	
	
	
}
