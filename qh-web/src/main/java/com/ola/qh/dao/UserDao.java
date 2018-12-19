package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;
import com.ola.qh.entity.UserCode;

public interface UserDao {

	public int saveUser(User user);
	
	public User loginUser(@Param("mobile")String mobile,@Param("password")String password);
	
	public User singleUser(@Param("userId")String userId,@Param("mobile")String mobile);
	
	public int updateUser(User user);
	
	public int updatePassword(User user);
	
	
	
	
	public int insertCode(UserCode uc);
	
	public UserCode singleCode(String mobile);
	
	public int updateCode(@Param("code")String code,@Param("mobile")String mobile);
	
	
}
