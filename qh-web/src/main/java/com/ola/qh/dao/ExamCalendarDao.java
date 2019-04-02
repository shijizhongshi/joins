package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.ExamCalendar;

public interface ExamCalendarDao {

	public List<ExamCalendar> calendarList(@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	
}
