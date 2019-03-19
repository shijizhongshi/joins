package com.ola.qh.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.User;
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
	public Results<List<UserMessage>> listType(@RequestParam(name = "userId", required = true) String userId) {

		Results<List<UserMessage>> result = new Results<List<UserMessage>>();
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		userId=userResult.getData().getId();
		List<UserMessage> list = new ArrayList<UserMessage>();
		//////// 1:
		List<UserMessage> list1 = userMessageService.listByType(userId, 1);
			//// 修改时间
		//////订单消息通知
		if (list1 != null && list1.size() != 0) {
			UserMessage userMessage= list1.get(0);
			Date date1 = userMessage.getAddtime();
			Date date2 = new Date();
			BigDecimal bdate2 = new BigDecimal(date2.getTime());
			BigDecimal bdate1 = new BigDecimal(date1.getTime());
			BigDecimal bd = new BigDecimal(24 * 60 * 60 * 1000);
			BigDecimal b = bdate2.subtract(bdate1);
			BigDecimal day = b.divide(bd).setScale(2, BigDecimal.ROUND_UP);
			if (day.compareTo(new BigDecimal(1)) != 1) {
				SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
				userMessage.setShowtime(sf.format(date1));
			} else {
				userMessage.setShowtime(Patterns.sfDetailTime(userMessage.getAddtime()));
			}
			list.add(userMessage);
		}
		/////收益消息通知()
		List<UserMessage> list4 = userMessageService.listByType(userId, 4);
		if (list4 != null && list4.size() != 0) {
			UserMessage userMessage= list4.get(0);
			Date date1 = userMessage.getAddtime();
			Date date2 = new Date();
			BigDecimal bdate2 = new BigDecimal(date2.getTime());
			BigDecimal bdate1 = new BigDecimal(date1.getTime());
			BigDecimal bd = new BigDecimal(24 * 60 * 60 * 1000);
			BigDecimal b = bdate2.subtract(bdate1);
			BigDecimal day = b.divide(bd).setScale(2, BigDecimal.ROUND_UP);
			if (day.compareTo(new BigDecimal(1)) != 1) {
				SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
				userMessage.setShowtime(sf.format(date1));
			} else {
				userMessage.setShowtime(Patterns.sfDetailTime(userMessage.getAddtime()));
			}
			list.add(userMessage);
		}
		/////// 5:这个是系统消息
		List<UserMessage> list5 = userMessageService.list(userId, 0, 0, 5);
		if (list5 != null && list5.size() != 0) {
			UserMessage userMessage= list5.get(0);
			Date date1 = userMessage.getAddtime();
			Date date2 = new Date();
			BigDecimal bdate2 = new BigDecimal(date2.getTime());
			BigDecimal bdate1 = new BigDecimal(date1.getTime());
			BigDecimal bd = new BigDecimal(24 * 60 * 60 * 1000);
			BigDecimal b = bdate2.subtract(bdate1);
			BigDecimal day = b.divide(bd).setScale(2, BigDecimal.ROUND_UP);
			if (day.compareTo(new BigDecimal(1)) != 1) {
				SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
				userMessage.setShowtime(sf.format(date1));
			} else {
				userMessage.setShowtime(Patterns.sfDetailTime(userMessage.getAddtime()));
			}
			list.add(userMessage);
		}
		
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	/**
	 * 消息列表
	 * 
	 * @param userId
	 * @param types
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public Results<List<UserMessage>> list(
			@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "types", required = true) int types,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<UserMessage>> result = new Results<List<UserMessage>>();
		List<UserMessage> list = new ArrayList<UserMessage>();
		int pageNo = (page - 1) * Patterns.zupageSize;
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		userId=userResult.getData().getId();
		list = userMessageService.list(userId, pageNo, Patterns.zupageSize, types);
		for (UserMessage userMessage : list) {
			userMessage.setShowtime(Patterns.sfDetailTime(userMessage.getAddtime()));
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}
}
