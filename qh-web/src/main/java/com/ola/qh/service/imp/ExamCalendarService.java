package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.ExamCalendarDao;
import com.ola.qh.entity.ExamCalendar;
import com.ola.qh.service.IExamCalendarService;

@Service
public class ExamCalendarService implements IExamCalendarService{

	@Autowired
	private ExamCalendarDao examCalendarDao;

	@Override
	public List<ExamCalendar> calendarList(String courseTypeSubclassName) {
		
		return examCalendarDao.calendarList(courseTypeSubclassName);
	}

	
}
