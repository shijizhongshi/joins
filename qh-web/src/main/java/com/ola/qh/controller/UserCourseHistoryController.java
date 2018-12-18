package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserCourseHistory;
import com.ola.qh.service.IUserCourseHistoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/usercoursehistory")
public class UserCourseHistoryController {

	@Autowired
	private IUserCourseHistoryService userCourseHistoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<UserCourseHistory>> selectUserCourseHistory(@RequestParam(name="userId",required=false)String userId){
		
		Results<List<UserCourseHistory>> results=new Results<List<UserCourseHistory>>();
		
		List<UserCourseHistory> list=userCourseHistoryService.selectUserCourseHistory(userId);
		
		if(list==null || list.size()==0){
			results.setMessage("当前没有观看历史");
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertUpdateUserCourseHistory(@RequestBody @Valid UserCourseHistory userCourseHistory,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		UserCourseHistory exist=userCourseHistoryService.existUserCourseHistory(userCourseHistory.getUserId(), userCourseHistory.getClassId());
		
		if(exist==null){
			
			userCourseHistory.setId(KeyGen.uuid());
			userCourseHistory.setAddtime(new Date());
			userCourseHistoryService.insertUserCourseHistory(userCourseHistory);
			
		}
		Date addtime=new Date();
		userCourseHistoryService.updateUserCourseHistory(userCourseHistory.getClassId(), userCourseHistory.getUserId(), addtime);
		
		
		results.setStatus("0");
		return results;
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteUserCourseHistory(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="userId",required=false)String userId){
		
		Results<String> results=new Results<String>();
		
		int delete=userCourseHistoryService.deleteUserCourseHistory(id, userId);
		
		if(delete==0){
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
		
	}
}
