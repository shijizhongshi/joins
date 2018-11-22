package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.IUserLoginService;
@Service
public class UserLoginService implements IUserLoginService{

	@Autowired
	private UserLoginDao userlogindao;
	@Override
	public List<UserLogin> selectUserLogin(String userId) {
		
		return userlogindao.selectUserLogin(userId);
	}

	@Override
	public int updateUserLogin(UserLogin userlogin) {
		
		return userlogindao.updateUserLogin(userlogin);
	}

}
