package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserWeixinBindingDao;
import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.service.IUserWeixinBindingService;

@Service
public class UserWeixinBindingService implements IUserWeixinBindingService{

	@Autowired
	private UserWeixinBindingDao userBindingDao;
	
	@Override
	public int existUserBinding(String userId) {
		
		return userBindingDao.existUserBinding(userId);
	}

	@Override
	public int saveUserBinding(UserWeixinBinding userbinding) {
		
		return userBindingDao.saveUserBinding(userbinding);
	}
	
}
