package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserBuyCourse;

public interface UserBuyCourseDao {

	public int insertUserCourse(UserBuyCourse ubc);
	
	public List<UserBuyCourse> selectUserBuyCourse(@Param("userId")String userId,@Param("types")int types,@Param("years")String years);
	
	public int selectUserBuyCourseCount(@Param("userId")String userId,
			@Param("classId")String classId,@Param("courseId")String courseId);
}
