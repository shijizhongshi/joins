package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserLogin;

public interface IUserLoginService {

	public List<UserLogin> selectUserLogin(String userId);
	
	public int updateUserLogin(UserLogin userlogin);
}
