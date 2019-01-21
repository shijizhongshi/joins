package com.ola.qh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserMessage;
import com.ola.qh.service.IUserMessageService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/usermessage")
public class UserMessageController {

	@Autowired
	private IUserMessageService userMessageService;
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping("/listType")
	public Results<List<UserMessage>> listType(
			@RequestParam(name="userId",required=true)String userId){
		
		Results<List<UserMessage>> result=new Results<List<UserMessage>>();
		List<UserMessage> list=new ArrayList<UserMessage>();
		////////1:这个是订单和提现的消息
		List<UserMessage> list1=userMessageService.listByType(userId,1);
		if(list1!=null && list1.size()!=0){
			list.add(list1.get(0));
		}
		///////5:这个是系统消息
		List<UserMessage> list5=userMessageService.list(userId, 0, 0, 5);
		if(list5!=null && list5.size()!=0){
			list.addAll(list5);
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}
	
	
	/**
	 * 消息列表
	 * @param userId
	 * @param types
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public Results<List<UserMessage>> list(
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="types",required=true)int types,
			@RequestParam(name="page",required=true)int page){
		
		Results<List<UserMessage>> result=new Results<List<UserMessage>>();
		List<UserMessage> list=new ArrayList<UserMessage>();
		int pageNo=(page-1)*Patterns.zupageSize;
		Results<String> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		list=userMessageService.list(userId, pageNo, Patterns.zupageSize, types);
		
		result.setStatus("0");
		result.setData(list);
		return result;
	}
}
