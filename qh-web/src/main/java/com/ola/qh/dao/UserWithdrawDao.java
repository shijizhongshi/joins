package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdraw;

public interface UserWithdrawDao {

	public List<UserWithdraw> selectUserWithdraw(@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("payStatus")String payStatus);
	
	public int saveUserWithdraw(UserWithdraw userwithdraw);
	
	public int deleteUserWithdraw(@Param("id")String id);
	
	public int updateUserWithdrawstatus(@Param("id")String id);
	
	public int existUserWithdraw(@Param("id")String id,@Param("payStatus")int payStatus);
	
}
