package com.ola.qh.service;

import com.ola.qh.entity.UserLogin;

public interface IUserLoginService {

	public UserLogin selectUserLogin(String userId);
	
	public int updateUserLogin(UserLogin userlogin);
}
