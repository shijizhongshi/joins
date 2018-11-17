package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.User;
import com.ola.qh.service.IUserService;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int saveUsers(User user) {
		
		return userDao.saveUser(user);
	}

	@Override
	public User loginUser(String mobile, String password) {
		
		return userDao.loginUser(mobile, password);
	}

	
	
}
