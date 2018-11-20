package com.ola.qh.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.User;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/existMobile", method = RequestMethod.GET)
	public Results<User> existMobileUser(@RequestParam(name = "mobile", required = true) String mobile) {

		Results<User> results = new Results<User>();

		User existMobile = userService.existMobileUser(mobile);
		if (existMobile != null) {
			results.setStatus("1");
			results.setMessage("手机号重复");
			return results;
		}

		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<User> saveUser(@RequestBody @Valid User user, BindingResult valid, HttpServletRequest request) {
		Results<User> result = new Results<User>();

		Pattern pattern = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
		pattern.matcher(user.getMobile()).matches();
		if (!pattern.matcher(user.getMobile()).matches()) {
			result.setStatus("1");
			result.setMessage("手机号格式有误");
			return result;
		}

		if (valid.hasErrors()) {
			result.setMessage("注册信息填写不完整,请检查");
			result.setStatus("1");
			return result;
		}
		return userService.saveUsers(user, request);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Results<User> loginUser(@RequestParam(name = "mobile", required = true) String mobile,
			@RequestParam(name = "password", required = true) String password) {

		Results<User> results = new Results<User>();

		User user = userService.loginUser(mobile, password);
		if (user == null) {
			results.setMessage("用户名或密码错误");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		results.setData(user);
		return results;
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.GET)
	public Results<String> updateUser(@RequestParam(name = "nickname", required = true) String nickname,
			@RequestParam(name = "headimg", required = true) String headimg,
			@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int user = userService.updateUser(nickname, headimg, id);
		if (user <= 0) {
			results.setMessage("更改异常");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

}
