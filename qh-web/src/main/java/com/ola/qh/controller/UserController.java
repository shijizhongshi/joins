package com.ola.qh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.User;
import com.ola.qh.seivice.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveUser(@RequestBody @Valid User user, BindingResult valid) {
		Results<String> result = new Results<String>();

		if (valid.hasErrors()) {
			result.setMessage("注册信息填写不完整,请检查");
			result.setStatus("1");
			return result;
		}
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
}
