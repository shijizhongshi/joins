package com.ola.qh.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;
import com.ola.qh.entity.UserCode;
import com.ola.qh.entity.UserTypeSubclass;

public interface UserDao {

	public int saveUser(User user);
	
	public User loginUser(@Param("mobile")String mobile,@Param("password")String password);
	
	public User singleUser(@Param("userId")String userId,@Param("mobile")String mobile);
	
	public int updateUser(User user);
	
	public int updatePassword(User user);
	
	
	
	
	public int insertCode(UserCode uc);
	
	public UserCode singleCode(String mobile);
	
	public int updateCode(@Param("code")String code,@Param("mobile")String mobile);

	public UserTypeSubclass selectByUserId(@Param("userId")String id);

	public int insertUserTypeSubclass(UserTypeSubclass newUserTypeSubclass);

	public Integer updateNameById(@Param("userId")String userId,@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("updateTime")Date updateTime);

}
