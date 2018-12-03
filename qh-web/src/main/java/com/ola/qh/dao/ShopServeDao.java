package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;

public interface ShopServeDao {

	public int insertServe(ShopServe ss);
	
	public int updateServe(ShopServe ss);
	
	public ShopServe selectSingle(String id);
	
	public List<ShopServe> selectList(
			@Param("shopId")String shopId,
			@Param("id")String id,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int insertServeImg(ShopServeImg ssi);
	
	public int deleteServeImg(String id);
	
	public List<ShopServeImg> selectByServeId(String serveId);
	
	
	
	
}
