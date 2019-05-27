package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionBankAsk;
import com.ola.qh.service.IQuestionBankAskService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/questionBankAsk")
public class QuestionBankAskController {

	@Autowired
	private IQuestionBankAskService questionBankAskService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<QuestionBankAsk>> questionBankAsklist(@RequestParam(name="page",required=true)int page,
			@RequestParam(name="courseTypeSubclassName",required=false)String courseTypeSubclassName){
		
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		return questionBankAskService.questionBankAsklist(courseTypeSubclassName, pageNo, pageSize);
	}
}
