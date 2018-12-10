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
	public List<ShopDrugCart> selectShopDrugCart(String userId, int pageNo, int pageSize) {
		
		return shopDrugCartDao.selectShopDrugCart(userId, pageNo, pageSize);
	}

	@Override
	public int insertShopDrugCart(ShopDrugCart shopDrugCart) {
		
		return shopDrugCartDao.insertShopDrugCart(shopDrugCart);
	}

	@Override
	public int deleteShopDrugCart(String id,String userId) {
		
		return shopDrugCartDao.deleteShopDrugCart(id,userId);
	}

	@Override
	public int updateShopDrugCart(int count, String id,Date updatetime) {
		
		return shopDrugCartDao.updateShopDrugCart(count, id,updatetime);
	}

	@Override
	public ShopDrugCart existShopDrugCart(String drugId, String userId) {
		
		return shopDrugCartDao.existShopDrugCart(drugId, userId);
	}

	
}
