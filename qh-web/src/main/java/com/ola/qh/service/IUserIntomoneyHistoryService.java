package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserIntomoneyHistory;

public interface IUserIntomoneyHistoryService {

	public List<UserIntomoneyHistory> selectUserIntomoneyHistory(String userId,int pageNo,int pageSize);
	
	public int saveUserIntomoneyHistory(UserIntomoneyHistory userIntomoneyHistory);
	
	public int deleteUserIntomoneyHistory(@Param("id")String id);
}
