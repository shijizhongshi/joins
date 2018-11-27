package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.util.Results;

public interface IUserWithdrawHistoryService {

	public List<UserWithdrawHistory> selectUserWithdrawHistory(String userId,int pageNo,int pageSize);
	
	public Results<String> saveUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory);
	
	public int deleteUserWithdrawHistory(String id);
}
