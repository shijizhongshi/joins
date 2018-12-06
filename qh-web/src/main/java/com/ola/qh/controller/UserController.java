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
	public Results<User> loginUser(@RequestBody @Valid UserLogin userlogin, BindingResult valid,HttpServletRequest request) {

		Results<User> result = new Results<User>();
		if (valid.hasErrors()) {
			result.setMessage("登录信息填写不完整,请检查");
			result.setStatus("1");
			return result;
		}
		return userService.loginUser(userlogin,request);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateUser(@RequestBody User user,HttpServletRequest  request) {

		Results<String> results = new Results<String>();

		if((user.getId()==null || "".equals(user.getId())) && (user.getMobile()==null || "".equals(user.getMobile()))){
			results.setStatus("1");
			results.setMessage("缺少用户的标识");
			return results;
		}
		if(user.getPassword()!=null && user.getPassword()!=""){
			///////验证一下验证码说明是修改密码的操作
			if(user.getVerification()==null || "".equals(user.getVerification())){
				results.setStatus("1");
				results.setMessage("修改密码的时候验证码不能为空");
				return results;
			}
			String verification = request.getSession().getAttribute(user.getMobile()).toString();
			if (!verification.equals(user.getVerification())) {
				results.setStatus("1");
				results.setMessage("验证码不正确,请稍后重新获取");
				return results;
			}
		}
		int users = userService.updateUser(user);
		if (users <= 0) {
			results.setMessage("更改异常");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	/*@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public Results<User> updatePassword(@RequestBody User user) {

		return userService.updatePassword(user);
	}*/
}
