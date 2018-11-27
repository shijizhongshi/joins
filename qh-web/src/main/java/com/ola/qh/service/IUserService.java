package com.ola.qh.service;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.util.Results;

public interface IUserService {
	public Results<User> saveUsers(User user, HttpServletRequest request);

	public Results<User> loginUser(UserLogin userlogin);

	public User existMobileUser(String mobile);

	public int updateUser(User user);
	
	public Results<String> existUser(String userId);
	
	
	
}
