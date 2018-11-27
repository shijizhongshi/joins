package com.ola.qh.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.service.IUserWithdrawHistoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userwithdrawhistory")
public class UserWithdrawHistoryController {

	@Autowired
	private IUserWithdrawHistoryService userWithdrawHistoryService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserWithdrawHistory>> selectUserWithdrawHistory(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<UserWithdrawHistory>> results = new Results<List<UserWithdrawHistory>>();

		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<UserWithdrawHistory> select = userWithdrawHistoryService.selectUserWithdrawHistory(userId, pageNo, pageSize);

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
	public Results<String> saveUserWithdrawHistory(@RequestBody UserWithdrawHistory userwithdrawhistory) {

		Results<String> results = new Results<String>();

		userwithdrawhistory.setId(KeyGen.uuid());
		userwithdrawhistory.setAddtime(new Date());
		int save = userWithdrawHistoryService.saveUserWithdrawHistory(userwithdrawhistory);

		if (save <= 0) {
			results.setMessage("添加失败");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserWithdrawHistory(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete = userWithdrawHistoryService.deleteUserWithdrawHistory(id);
		if (delete <= 0) {
			results.setMessage("删除失败");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}