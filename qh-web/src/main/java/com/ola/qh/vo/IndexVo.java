package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Banner;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.News;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;

public class IndexVo {

	private List<Shop> shoplist=new ArrayList<Shop>();
	
	private List<News> newlist=new ArrayList<News>();
	
	private List<CourseClass> classlist=new ArrayList<CourseClass>(); 
	
	private List<ShopDrug> druglist=new ArrayList<ShopDrug>();
	
	private List<Banner> bannerlist=new ArrayList<Banner>();

	public List<Shop> getShoplist() {
		return shoplist;
	}

	public void setShoplist(List<Shop> shoplist) {
		this.shoplist = shoplist;
	}

	public List<News> getNewlist() {
		return newlist;
	}

	public void setNewlist(List<News> newlist) {
		this.newlist = newlist;
	}

	
	public List<CourseClass> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<CourseClass> classlist) {
		this.classlist = classlist;
	}

	public List<ShopDrug> getDruglist() {
		return druglist;
	}

	public void setDruglist(List<ShopDrug> druglist) {
		this.druglist = druglist;
	}

	public List<Banner> getBannerlist() {
		return bannerlist;
	}

	public void setBannerlist(List<Banner> bannerlist) {
		this.bannerlist = bannerlist;
	} 

	
}
