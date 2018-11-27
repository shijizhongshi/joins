package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.util.Results;

public interface UserWithdrawHistoryDao {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Results<String> saveUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	public int deleteUserWithdrawHistory(@Param("id")String id);
	
	
}
