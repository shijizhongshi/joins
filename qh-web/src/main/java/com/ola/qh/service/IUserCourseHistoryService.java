package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.UserCourseHistory;

public interface IUserCourseHistoryService {

	public List<UserCourseHistory> selectUserCourseHistory(String userId,int pageNo,int pageSize);
	
	public int insertUserCourseHistory(UserCourseHistory userCourseHistory);
	
	public int updateUserCourseHistory(String classId,String userId,Date addtime);
	
	public int deleteUserCourseHistory(String id,String userId);
	
	public UserCourseHistory existUserCourseHistory(String userId,String classId);
}
