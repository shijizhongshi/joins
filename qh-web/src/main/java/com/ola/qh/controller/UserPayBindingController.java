package com.ola.qh.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserPayBinding;
import com.ola.qh.service.IUserPayBindingService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value = "/api/userpaybinding")
public class UserPayBindingController {

	@Autowired
	private IUserPayBindingService userPayBindingService;

	@RequestMapping(value = "select", method = RequestMethod.GET)
	public Results<UserPayBinding> selectUserPayBinding(@RequestParam(name = "userId", required = true) String userId) {

		Results<UserPayBinding> results = new Results<UserPayBinding>();

		UserPayBinding select = userPayBindingService.selectUserPayBinding(userId);
		if (select == null) {
			results.setMessage("显示错误");
			results.setStatus("1");
			return results;
		}
		results.setData(select);
		results.setStatus("0");
		return results;

	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Results<String> saveUserPayBinding(@RequestBody UserPayBinding userpaybinding) {

		Results<String> results = new Results<String>();

		userpaybinding.setId(KeyGen.uuid());
		userpaybinding.setAddtime(new Date());
		int select = userPayBindingService.saveUserPayBinding(userpaybinding);

		if (select <= 0) {
			results.setMessage("添加错误");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Results<String> updateUserPayBinding(@RequestBody UserPayBinding userpaybinding) {

		Results<String> results = new Results<String>();

		userpaybinding.setUpdatetime(new Date());
		int update = userPayBindingService.updateUserPayBinding(userpaybinding);

		if (update <= 0) {
			results.setMessage("添加支付信息错误");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public Results<String> deleteUserPayBinding(@RequestParam(name = "userId", required = true) String userId) {

		Results<String> results = new Results<String>();

		int delete = userPayBindingService.deleteUserPayBinding(userId);
		if (delete <= 0) {
			results.setMessage("删除错误");
			results.setStatus("1");
			return results;
		}

		results.setStatus("0");
		return results;

	}
}
