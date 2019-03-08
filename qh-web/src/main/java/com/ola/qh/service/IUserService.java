package com.ola.qh.service;

import javax.servlet.http.HttpServletRequest;

import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserCode;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.util.Results;

public interface IUserService {
	public Results<User> saveUsers(User user, HttpServletRequest request);

	public Results<User> loginUser(UserLogin userlogin,HttpServletRequest request);

	public User sinleUser(String userId,String mobile);

	public int updateUser(User user);
	
	public Results<String> existUser(String userId);
	
	public Results<User> updatePassword(User user);
	
	public int insertCode(UserCode uc);
	
	public UserCode singleCode(String mobile);
	
	public int updateCode(String code,String mobile);
	
	public UserBook singleUserBook(String userId);
	
	public Integer selectByMobileAndPassword(String mobile,String password,HttpServletRequest request);
}
