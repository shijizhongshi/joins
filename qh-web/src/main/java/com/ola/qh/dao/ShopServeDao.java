package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopServeDomain;

public interface ShopServeDao {

	public int insertServe(ShopServe ss);
	
	public int updateServe(ShopServe ss);
	
	public ShopServe selectSingle(String id);
	
	public List<ShopServe> selectList(ShopServeDomain ssd);
	
	public int selectListCount(@Param("shopId")String shopId,@Param("serveStatus")int serveStatus);
	
	public int insertServeImg(ShopServeImg ssi);
	
	public int deleteServeImg(String id);
	
	public List<ShopServeImg> selectByServeId(String serveId);
	
	
	public List<Shop> selectShop(ShopDomain sd);
	
	
	
	
}
