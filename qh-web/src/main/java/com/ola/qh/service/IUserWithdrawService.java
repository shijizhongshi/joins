package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserWithdrawVo;

public interface IUserWithdrawService {

	public Results<List<UserWithdrawVo>> selectUserWithdraw(String userId,int pageNo,int pageSize,String payStatus);
	
	public Results<String> saveUserWithdraw(UserWithdraw userwithdrawhistory);
	
	public int deleteUserWithdraw(String id);
	
	public int updateUserWithdrawstatus(String id);
	
	public int existUserWithdraw(String id,int payStatus);
}
