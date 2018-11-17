package com.ola.qh.service;

import com.ola.qh.entity.User;

public interface IUserService {
	public int saveUsers(User user);
	
	public User loginUser(String mobile,String password);
	
	public User existMobileUser(String mobile);
}
