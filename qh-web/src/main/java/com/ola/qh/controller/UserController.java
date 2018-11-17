package com.ola.qh.controller;

import java.util.Date;
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
import com.ola.qh.util.KeyGen;
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
		if (existMobile == null) {

			results.setStatus("0");
			return results;
		}
		results.setStatus("1");
		results.setMessage("手机号重复");
		return results;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveUser(@RequestBody @Valid User user, BindingResult valid, HttpServletRequest request) {
		Results<String> result = new Results<String>();

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

		String verification = request.getSession().getAttribute(user.getMobile()).toString();
		if (verification.equals(user.getVerification())) {

			User existMobile = userService.existMobileUser(user.getMobile());

			if (existMobile == null) {
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
			result.setMessage("手机号已存在");
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
