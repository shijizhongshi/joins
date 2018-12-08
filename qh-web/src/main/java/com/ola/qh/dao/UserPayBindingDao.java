package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserPayBinding;

public interface UserPayBindingDao {

	public UserPayBinding selectUserPayBinding(@Param("userId")String userId);
	
	public int saveUserPayBinding(UserPayBinding userpaybinding);
	
	public int updateUserPayBinding(UserPayBinding userpaybinding);
	
	public int deleteUserPayBinding(@Param("id")String id);
	
	public UserPayBinding existUserPayBinding(@Param("userId")String userId);
}
