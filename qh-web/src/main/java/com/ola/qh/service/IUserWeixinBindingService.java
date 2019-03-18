package com.ola.qh.service;


import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.util.Results;

public interface IUserWeixinBindingService {

	public int saveUserBinding(UserWeixinBinding userpaybinding);
	
	public Results<String> existUserBinding(String userId);
	
	
	
}
