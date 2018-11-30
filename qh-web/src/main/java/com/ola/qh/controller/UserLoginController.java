package com.ola.qh.controller;

import java.util.Date;
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
	public Results<UserLogin> selectUserLogin(@RequestParam(name = "userId", required = true) String userId) {

		Results<UserLogin> results = new Results<UserLogin>();

		UserLogin select = userloginservice.selectUserLogin(userId);
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
		
		if(userlogin.getUserId()==null || "".equals(userlogin.getUserId())){
			results.setStatus("1");
			results.setMessage("缺少用户ID");
			return results;
		}
		
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
