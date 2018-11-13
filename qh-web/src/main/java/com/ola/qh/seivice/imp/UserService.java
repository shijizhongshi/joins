package com.ola.qh.seivice.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.User;
import com.ola.qh.seivice.IUserService;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int saveUsers(User user) {
		
		return userDao.saveUser(user);
	}

	
	
}
