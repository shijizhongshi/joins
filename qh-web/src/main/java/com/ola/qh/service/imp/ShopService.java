package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.service.IShopService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class ShopService implements IShopService{

	@Autowired
	private ShopDao shopDao;
	
	@Override
	public Results<String> shopSaveUpdate(Shop shop) {
		
		Results<String> result = new Results<String>();
		if(shop.getId()!=null && !"".equals(shop.getId())){
			///////修改店铺的基本信息
			shop.setUpdatetime(new Date());
			int num = shopDao.updateShop(shop);
			if(num>0){
				result.setStatus("0");
				return result;
			}
			result.setStatus("1");
			result.setMessage("更新店铺信息失败");
			return result;
			
			
		}
		///////保存用户的基本信息
		shop.setId(KeyGen.uuid());
		shop.setAddtime(new Date());
		int num = shopDao.insertShop(shop);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("店铺信息保存失败");
		return result;
	}

	@Override
	public List<Shop> selectShopByUserId(String userId){
		// TODO Auto-generated method stub
		return shopDao.selectShopByUserId(userId);
	}

}
