package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;

public class DrugIndexVo {

	private List<ShopDrug>  timeList=new ArrayList<ShopDrug>();///限时抢购
	
	private List<Banner> bannerList=new ArrayList<Banner>();///商城首页的banner集合
	
	private List<Banner> hotlist=new ArrayList<Banner>();////品牌热卖的banner
	
	private String bannerUrl;
	
	private List<Shop> shoplist=new ArrayList<Shop>();
	
	private List<ShopDrug> druglist=new ArrayList<ShopDrug>();

	public List<ShopDrug> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<ShopDrug> timeList) {
		this.timeList = timeList;
	}

	public List<Banner> getHotlist() {
		return hotlist;
	}

	public void setHotlist(List<Banner> hotlist) {
		this.hotlist = hotlist;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public List<Shop> getShoplist() {
		return shoplist;
	}

	public void setShoplist(List<Shop> shoplist) {
		this.shoplist = shoplist;
	}

	public List<ShopDrug> getDruglist() {
		return druglist;
	}

	public void setDruglist(List<ShopDrug> druglist) {
		this.druglist = druglist;
	}

	public List<Banner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<Banner> bannerList) {
		this.bannerList = bannerList;
	}
	
	
	
}
