package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/youmei")
public class YoumeiWeb {

	@RequestMapping("/index")
	public String index(){
		return "youmei/index";
	}
	@RequestMapping("/login")
	public String login(){
		return "youmei/login";
	}
	}
