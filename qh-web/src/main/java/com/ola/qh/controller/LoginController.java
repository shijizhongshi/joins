package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.ILoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Integer loginString (@RequestParam(name = "mobile",required = true)String mobile,
			@RequestParam(name = "password",required = true) String password) {
		Integer count = loginService.selectCount(mobile,password); 
		
		return count;
	}
	
}
