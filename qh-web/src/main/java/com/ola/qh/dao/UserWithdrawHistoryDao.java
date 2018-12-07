package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdrawHistory;

public interface UserWithdrawHistoryDao {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int saveUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	public int deleteUserWithdrawHistory(@Param("id")String id);
	
	public int updateUserWithdrawHistorystatus(@Param("id")String id);
	
	public int existUserWithdrawHistory(@Param("id")String id,@Param("payStatus")int payStatus);
	
}
