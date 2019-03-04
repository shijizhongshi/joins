package com.ola.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionsWeb {

	@RequestMapping("/questionBank")
	public String questionBank(){
		return "questionBank";
	}
}
