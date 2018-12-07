package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserPayBindingDao;
import com.ola.qh.entity.UserPayBinding;
import com.ola.qh.service.IUserPayBindingService;

@Service
public class UserPayBindingService implements IUserPayBindingService{

	@Autowired
	private UserPayBindingDao userPayBindingDao;
	
	@Override
	public UserPayBinding selectUserPayBinding(String userId) {
		
		return userPayBindingDao.selectUserPayBinding(userId);
	}

	@Override
	public int saveUserPayBinding(UserPayBinding userpaybinding) {
		
		return userPayBindingDao.saveUserPayBinding(userpaybinding);
	}

	@Override
	public int updateUserPayBinding(UserPayBinding userpaybinding) {
		
		return userPayBindingDao.updateUserPayBinding(userpaybinding);
	}

	@Override
	public int deleteUserPayBinding(String id) {
		
		return userPayBindingDao.deleteUserPayBinding(id);
	}

	
}
