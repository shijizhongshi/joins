package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserFinishDays;

public interface UserFinishDaysDao {

	public UserFinishDays UserFinishDaysSingle(@Param("userId")String userId,@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	public int insertUserFinishDays(UserFinishDays userFinishDays);
	
	public int UserFinishDaysUpdate(UserFinishDays userFinishDays);
}
