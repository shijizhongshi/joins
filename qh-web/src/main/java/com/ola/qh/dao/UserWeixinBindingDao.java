package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWeixinBinding;

public interface UserWeixinBindingDao {

	public UserWeixinBinding selectUserBinding(@Param("userId")String userId);
	
	public int saveUserBinding(UserWeixinBinding userpaybinding);
	
	public int existUserBinding(@Param("userId")String userId);
}
