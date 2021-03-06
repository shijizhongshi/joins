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
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserCode;
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

	@RequestMapping(value = "/single", method = RequestMethod.GET)
	public Results<User> singleUser(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "mobile", required = false) String mobile) {

		Results<User> results = new Results<User>();
		User user = new User();
		if (userId != null && !"".equals(userId)) {
			
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				results.setStatus("1");
				results.setMessage(userResult.getMessage());
				return results;
			}
			userId=userResult.getData().getId();
			
			user = userService.sinleUser(userId, null);
		} else if (mobile != null && !"".equals(mobile)) {
			user = userService.sinleUser(null, mobile);
		}

		if (user.getNickname() == null || "".equals(user.getNickname())) {
			user.setNickname(user.getMobile().substring(7));
		}
		user.setPassword(null);
		results.setData(user);
		results.setStatus("0");
		return results;
	}
	/**
	 *注册
	 * @param user
	 * @param valid
	 * @param request
	 * @return
	 */
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
	/**
	 *	手机端用户登录(第一次登录)
	 * @param userlogin
	 * @param valid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Results<User> loginUser(@RequestBody @Valid UserLogin userlogin, BindingResult valid,
			HttpServletRequest request) {

		Results<User> result = new Results<User>();
		if (valid.hasErrors()) {
			result.setMessage("登录信息填写不完整,请检查");
			result.setStatus("1");
			return result;
		}
		return userService.loginUser(userlogin, request);
	}
	/**
	 * 用户在登录状态下再次进入app
	 */
	@RequestMapping(value = "/loginAgain",method = RequestMethod.GET)
	public Results<String> loginAgain (@RequestParam(name = "courseTypeSubclassName") String courseTypeSubclassName,
			@RequestParam(name = "userId") String userId,
			HttpServletRequest request) {
		Results<String> result = new Results<String>();
		result = userService.loginAgainUser(courseTypeSubclassName,userId);
				
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateUser(@RequestBody User user, HttpServletRequest request) {

		Results<String> results = new Results<String>();
		if ((user.getId() == null || "".equals(user.getId()))
				&& (user.getMobile() == null || "".equals(user.getMobile()))) {
			results.setStatus("1");
			results.setMessage("缺少用户的标识");
			return results;
		}
		if (user.getId() != null && !"".equals(user.getId())) {
			
			Results<User> userResult = userService.existUser(user.getId());
			if("1".equals(userResult.getStatus())){
				results.setStatus("1");
				results.setMessage(userResult.getMessage());
				return results;
			}
			user.setId(userResult.getData().getId());
		}
		
		if (user.getPassword() != null && user.getPassword() != "") {
			/////// 验证一下验证码说明是修改密码的操作
			if (user.getVerification() == null || "".equals(user.getVerification())) {
				results.setStatus("1");
				results.setMessage("修改密码的时候验证码不能为空");
				return results;
			}
			// String verification =
			// request.getSession().getAttribute(user.getMobile()).toString();
			UserCode uc = userService.singleCode(user.getMobile());

			if (!uc.getCode().equals(user.getVerification())) {
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

	/*
	 * @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	 * public Results<User> updatePassword(@RequestBody User user) {
	 * 
	 * return userService.updatePassword(user); }
	 */
	@RequestMapping("/single/userbook")
	public Results<UserBook> singleUserBook(@RequestParam(name = "userId", required = true) String userId) {

		Results<UserBook> result = new Results<UserBook>();
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		userId=userResult.getData().getId();
		UserBook ub = userService.singleUserBook(userId);
		result.setStatus("0");
		result.setData(ub);
		return result;
	}

	/**
	 * 用户登录验证
	 * 
	 * @param mobile   手机号
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value = "/web/login", method = RequestMethod.GET)
	public Results<String> loginByMobileAndPassword(@RequestParam(name = "mobile", required = true) String mobile,
			@RequestParam(name = "password", required = true) String password, HttpServletRequest request) {
		Results<String> results = new Results<String>();

		Integer count = userService.selectByMobileAndPassword(mobile, password, request);
		results.setStatus(String.valueOf(count));

		return results;
	}

	/**
	 * 用户web端注册
	 * 
	 * @param mobile
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/web/registe", method = RequestMethod.POST)
	public Results<User> registe(@RequestBody User user,
			@RequestParam(name = "password", required = true) String password, HttpServletRequest request) {
		Results<User> results = new Results<User>();

		if (!password.equals(user.getPassword())) {
			results.setStatus("1");
			results.setMessage("密码两次输入不一致，请核对");

			return results;
		}
		results = userService.saveUser(user, request);

		return results;
	}
	
	
}
