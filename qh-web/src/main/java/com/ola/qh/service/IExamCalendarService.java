package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.ExamCalendar;

public interface IExamCalendarService {

	public List<ExamCalendar> calendarList(String courseTypeSubclassName);
	
	
}
