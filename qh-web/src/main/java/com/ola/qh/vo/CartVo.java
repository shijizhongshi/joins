package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.ShopDrugCart;

public class CartVo {

	private String shopName;
	
	private List<ShopDrugCart> cartlist=new ArrayList<ShopDrugCart>();

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<ShopDrugCart> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<ShopDrugCart> cartlist) {
		this.cartlist = cartlist;
	}
	
	
}
