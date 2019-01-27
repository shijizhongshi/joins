package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Shop;

public class SearchVo {

	public List<SearchProductVo> productVo=new ArrayList<SearchProductVo>();
	
	public List<SearchProductVo> shopVo=new ArrayList<SearchProductVo>();

	public List<SearchProductVo> getProductVo() {
		return productVo;
	}

	public void setProductVo(List<SearchProductVo> productVo) {
		this.productVo = productVo;
	}

	public List<SearchProductVo> getShopVo() {
		return shopVo;
	}

	public void setShopVo(List<SearchProductVo> shopVo) {
		this.shopVo = shopVo;
	}

	
	
}
