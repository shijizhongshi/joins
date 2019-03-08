package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@RequestMapping("/registe")
	public String registe(){
		return "youmei/registe";
	}
	@RequestMapping("/onliecourse")
	public String OnlieCourse(){
		return "youmei/Onlie-course";
	}
	@RequestMapping("/particulars")
	public String particulars(){
		return "youmei/particulars";
	}
	@RequestMapping("/user")
	public String user(){
		return "youmei/user";
	}
	@RequestMapping("/user/user-message")
	public String userMessage(){
		return "youmei/user/user-message";
	}
	@RequestMapping("/user/user-grade")
	public String userGrade(){
		return "youmei/user/user-grade";
	}
	@RequestMapping("/user/user-curriculum")
	public String userCurriculum(){
		return "youmei/user/user-curriculum";
	}
	}
