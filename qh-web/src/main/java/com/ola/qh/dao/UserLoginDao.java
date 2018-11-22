package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserLogin;

public interface UserLoginDao {

	public List<UserLogin> selectUserLogin(@Param("userId")String userId);
	
	public int saveUserLogin(UserLogin userlogin);
	
	public int updateUserLogin(UserLogin userlogin);
}
