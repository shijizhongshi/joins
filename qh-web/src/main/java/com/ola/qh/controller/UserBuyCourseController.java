package com.ola.qh.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IUserBuyCourseService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userbuycourse")
public class UserBuyCourseController {

	@Autowired
	private IUserBuyCourseService userBuyCourseService;
	@Autowired
	private IUserService userService;
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
		int type=0;
		if(types!=null && !"".equals(types)){
			type=Integer.valueOf(types);
		}
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		userId=userResult.getData().getId();
		List<UserBuyCourse> list=userBuyCourseService.selectUserBuyCourse(userId,mobile,type,years);
		for (UserBuyCourse userBuyCourse : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userBuyCourse.setShowtime(sf.format(userBuyCourse.getAddtime()));
		}
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
