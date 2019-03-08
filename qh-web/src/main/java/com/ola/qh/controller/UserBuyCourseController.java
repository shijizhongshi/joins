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
	/**
	 * 
	 * <p>Title: selectUserBuyCourse</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @param mobile
	 * @param types  1:我的班级   2:我的课程
	 * @return
	 */
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<UserBuyCourse>> selectUserBuyCourse(
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="mobile",required=false)String mobile,
			@RequestParam(name="types",required=false)String types,
			@RequestParam(name="years",required=false)String years){
		
		Results<List<UserBuyCourse>> results=new Results<List<UserBuyCourse>>();
		
		
		List<UserBuyCourse> list=userBuyCourseService.selectUserBuyCourse(userId,mobile,Integer.valueOf(types),years);
		
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
