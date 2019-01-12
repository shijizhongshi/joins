package com.ola.qh.service;


import com.ola.qh.entity.UserWeixinBinding;

public interface IUserWeixinBindingService {

	public int saveUserBinding(UserWeixinBinding userpaybinding);
	
	public int existUserBinding(String userId);
	
	
}
