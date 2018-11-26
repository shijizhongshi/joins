package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserIntomoneyHistory;
import com.ola.qh.service.IUserIntomoneyHistoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userintomoneyhistory")
public class UserIntomoneyHistoryController {

	@Autowired
	private IUserIntomoneyHistoryService userIntomoneyHistoryService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserIntomoneyHistory>> selectUserIntomoneyHistory(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<UserIntomoneyHistory>> results = new Results<List<UserIntomoneyHistory>>();

		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<UserIntomoneyHistory> select = userIntomoneyHistoryService.selectUserIntomoneyHistory(userId, pageNo, pageSize);

		
				
		if (select== null) {
			results.setMessage("显示错误");
			results.setStatus("1");
			return results;
			
		}
		results.setData(select);
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveUserIntomoneyHistory(@RequestBody UserIntomoneyHistory userIntomoneyHistory) {

		Results<String> results = new Results<String>();

		userIntomoneyHistory.setId(KeyGen.uuid());
		userIntomoneyHistory.setAddtime(new Date());
		int save = userIntomoneyHistoryService.saveUserIntomoneyHistory(userIntomoneyHistory);

		if (save <= 0) {
			results.setMessage("添加失败");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserIntomoneyHistory(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete = userIntomoneyHistoryService.deleteUserIntomoneyHistory(id);
		if (delete <= 0) {
			results.setMessage("删除失败");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
