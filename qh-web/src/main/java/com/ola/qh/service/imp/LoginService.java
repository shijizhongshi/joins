package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserDao;
import com.ola.qh.service.ILoginService;

@Service
public class LoginService implements ILoginService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Integer selectCount(String mobile, String password) {
		Integer countInteger = userDao.selectCount(mobile,password);
		
		return countInteger;
	}

}
