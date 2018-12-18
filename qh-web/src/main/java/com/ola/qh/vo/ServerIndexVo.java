package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopServe;

public class ServerIndexVo {

	private List<Banner> bannerlist = new ArrayList<Banner>();////banner集合
	
	private List<Shop> nearbylist=new ArrayList<Shop>();///附近的店铺
	
	private List<ShopServe> servelist=new ArrayList<ShopServe>();///重点推出的项目
	
	private List<Shop> shopList=new ArrayList<Shop>();////猜你喜欢的店铺的集合

	public List<Banner> getBannerlist() {
		return bannerlist;
	}

	public void setBannerlist(List<Banner> bannerlist) {
		this.bannerlist = bannerlist;
	}

	public List<Shop> getNearbylist() {
		return nearbylist;
	}

	public void setNearbylist(List<Shop> nearbylist) {
		this.nearbylist = nearbylist;
	}

	public List<ShopServe> getServelist() {
		return servelist;
	}

	public void setServelist(List<ShopServe> servelist) {
		this.servelist = servelist;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
	
}
