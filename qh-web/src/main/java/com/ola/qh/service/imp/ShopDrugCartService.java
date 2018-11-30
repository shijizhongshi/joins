package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ola.qh.dao.ShopDrugCartDao;
import com.ola.qh.entity.ShopDrugCart;
import com.ola.qh.service.IShopDrugCartService;

@Service
public class ShopDrugCartService implements IShopDrugCartService{

	@Autowired
	private ShopDrugCartDao shopDrugCartDao;
	
	@Override
	public List<ShopDrugCart> selectShopDrugCart(String userId) {
		
		return shopDrugCartDao.selectShopDrugCart(userId);
	}

	@Override
	public int insertShopDrugCart(ShopDrugCart shopDrugCart) {
		
		return shopDrugCartDao.insertShopDrugCart(shopDrugCart);
	}

	@Override
	public int deleteShopDrugCart(String id) {
		
		return shopDrugCartDao.deleteShopDrugCart(id);
	}

	@Override
	public int deleteAllShopDrugCart(String userId) {
		
		return shopDrugCartDao.deleteAllShopDrugCart(userId);
	}

	@Override
	public int updateShopDrugCart(int count, String id,Date updatetime) {
		
		return shopDrugCartDao.updateShopDrugCart(count, id,updatetime);
	}

	
}
