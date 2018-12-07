package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ShopServeTypeDao;
import com.ola.qh.entity.ShopServeType;
import com.ola.qh.service.IShopServeTypeService;

@Service
public class ShopServeTypeService implements IShopServeTypeService {

	@Autowired
	private ShopServeTypeDao shopServeTypeDao;

	@Override
	public List<ShopServeType> selectShopServeType() {

		return shopServeTypeDao.selectShopServeType();
	}

	

}
