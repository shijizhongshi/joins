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
import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName:
 * @Description: 用户的注册，用户信息的修改与验证码识别
 * @author guozihan
 * @date
 *
 */
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Results<User> loginUser(@RequestBody @Valid UserLogin userlogin, BindingResult valid) {

		return userService.loginUser(userlogin);
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public Results<String> updateUser(@RequestBody User user) {

		Results<String> results = new Results<String>();

		int users = userService.updateUser(user);
		if (users <= 0) {
			results.setMessage("更改异常");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public Results<User> updatePassword(@RequestBody User user) {

		return userService.updatePassword(user);
	}
}
