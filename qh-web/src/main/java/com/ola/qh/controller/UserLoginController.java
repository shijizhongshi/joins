package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.IUserLoginService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value = "/api/userlogin")
public class UserLoginController {

	@Autowired
	private IUserLoginService userloginservice;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserLogin>> selectUserLogin(@RequestParam(name = "userId", required = true) String userId) {

		Results<List<UserLogin>> results = new Results<List<UserLogin>>();

		List<UserLogin> select = userloginservice.selectUserLogin(userId);
		if (select == null) {
			results.setMessage("出错了");
			results.setStatus("1");
			return results;
		}
		results.setData(select);
		results.setStatus("0");
		return results;
	}

	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateUserLogin(@RequestBody UserLogin userlogin) {

		Results<String> results = new Results<String>();
		userlogin.setUpdatetime(new Date());
		int update = userloginservice.updateUserLogin(userlogin);
		if (update <= 0) {
			results.setMessage("出错了");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
