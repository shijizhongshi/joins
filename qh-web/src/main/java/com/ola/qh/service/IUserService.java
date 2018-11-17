package com.ola.qh.service;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;

public interface IUserService {
	public int saveUsers(User user);
	
	public User loginUser(String mobile,String password);
	
	public User existMobileUser(String mobile);
	
	public int updateUser(@Param("nickname")String nickname,@Param("headimg")String headimg,@Param("id")String id);
}
