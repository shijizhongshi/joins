package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserCourseHistory;

public interface UserCourseHistoryDao {

	public List<UserCourseHistory> selectUserCourseHistory(@Param("userId")String userId);
	
	public int insertUserCourseHistory(UserCourseHistory userCourseHistory);
	
	public int updateUserCourseHistory(@Param("classId")String classId,@Param("userId")String userId,@Param("addtime")Date addtime);
	
	public int deleteUserCourseHistory(@Param("id")String id,@Param("userId")String userId);
	
	public UserCourseHistory existUserCourseHistory(@Param("userId")String userId,@Param("classId")String classId);
}
