package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserIntomoneyHistory;

public interface UserIntomoneyHistoryDao {

	public List<UserIntomoneyHistory> selectUserIntomoneyHistory(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int saveUserIntomoneyHistory(UserIntomoneyHistory userIntomoneyHistory);
	
	public int deleteUserIntomoneyHistory(@Param("id")String id);
}
