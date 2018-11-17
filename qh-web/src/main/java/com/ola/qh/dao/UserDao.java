package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;

public interface UserDao {

	public int saveUser(User user);
	
	public User loginUser(@Param("mobile")String mobile,@Param("password")String password);
	
	public User existMobileUser(@Param("mobile")String mobile);
	
	public int updateUser(@Param("nickname")String nickname,@Param("headimg")String headimg,@Param("id")String id);
	
	
}
