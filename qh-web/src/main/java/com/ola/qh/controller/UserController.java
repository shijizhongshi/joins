package com.ola.qh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.google.zxing.Result;
import com.ola.qh.entity.User;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveUser(@RequestBody @Valid User user, BindingResult valid, HttpServletRequest request) {
		Results<String> result = new Results<String>();

		if (valid.hasErrors()) {
			result.setMessage("注册信息填写不完整,请检查");
			result.setStatus("1");
			return result;
		}

		String verification = request.getSession().getAttribute(user.getMobile()).toString();
		if (verification.equals(user.getVerification())) {

			user.setAddtime(new Date());
			user.setId(KeyGen.uuid());

			int num = userService.saveUsers(user);
			if (num > 0) {
				result.setStatus("0");
				return result;

			}

			result.setStatus("1");
			result.setMessage("注册用户有误");
			return result;
		}

		result.setStatus("1");
		result.setMessage("验证码有误");
		return result;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Results<User> loginUser(@RequestParam(name = "mobile", required = true) String mobile,
			@RequestParam(name = "password", required = true) String password) {

		Results<User> results = new Results<User>();

		User user = userService.loginUser(mobile, password);
		if (user != null) {
			results.setStatus("0");
			results.setData(user);
			return results;
		}
		results.setMessage("用户名或密码错误");
		results.setStatus("1");
		return results;
	}
}
