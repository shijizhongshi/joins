package com.ola.qh.service;

import com.ola.qh.entity.UserPayBinding;

public interface IUserPayBindingService {

	public UserPayBinding selectUserPayBinding(String userId);
	
	public int saveUserPayBinding(UserPayBinding userpaybinding);
	
	public int updateUserPayBinding(UserPayBinding userpaybinding);
	
	public int deleteUserPayBinding(String id);
	
	public UserPayBinding existUserPayBinding(String userId);
}
