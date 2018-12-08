package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IUserBuyCourseService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userbuycourse")
public class UserBuyCourseController {

	@Autowired
	private IUserBuyCourseService userBuyCourseService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<UserBuyCourse>> selectUserBuyCourse(@RequestParam(name="userId",required=true)String userId){
		
		Results<List<UserBuyCourse>> results=new Results<List<UserBuyCourse>>();
		
		List<UserBuyCourse> list=userBuyCourseService.selectUserBuyCourse(userId);
		
		if(list==null || list.size()==0){
			results.setStatus("1");
			return results;
			
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
