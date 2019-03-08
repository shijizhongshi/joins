package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/youmei")
public class YoumeiWeb {

	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
		}
		return "youmei/index";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
		}
		return "youmei/login";
	}
	@RequestMapping("/registe")
	public String registe(){
		return "youmei/registe";
	}
	@RequestMapping("/onliecourse")
	public String OnlieCourse(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
		}
		return "youmei/Onlie-course";
	}
	@RequestMapping("/particulars")
	public String particulars(){
		return "youmei/particulars";
	}
	@RequestMapping("/user")
	public String user(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
			return "youmei/login";
		}
		return "youmei/user";
	}
	@RequestMapping("/user/user-message")
	public String userMessage(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
			return "youmei/login";
		}
		return "youmei/user/user-message";
	}
	@RequestMapping("/user/user-grade")
	public String userGrade(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
			return "youmei/login";
		}
		return "youmei/user/user-grade";
	}
	@RequestMapping("/user/user-curriculum")
	public String userCurriculum(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("islogin");
		if(obj==null){
			request.getSession().setAttribute("islogin", "0");
			request.getSession().setAttribute("username", "0");
			return "youmei/login";
		}
		return "youmei/user/user-curriculum";
	}

	
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request){
		request.getSession().setAttribute("islogin", "0");
		request.getSession().setAttribute("username", "0");
		return "youmei/login";
	}
	
	@RequestMapping("/vadio")
	public String vadio(){
		return "youmei/vadio";
	}

	}
