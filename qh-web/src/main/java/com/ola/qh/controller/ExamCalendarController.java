package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ExamCalendar;
import com.ola.qh.service.IExamCalendarService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/calendar")
public class ExamCalendarController {

	@Autowired
	private IExamCalendarService examCalendarService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<ExamCalendar>> calendarList(@RequestParam(name="courseTypeSubclassName",required=true)String courseTypeSubclassName){
		
		Results<List<ExamCalendar>> results=new Results<List<ExamCalendar>>();
		
		List<ExamCalendar> list=examCalendarService.calendarList(courseTypeSubclassName);
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	
}
