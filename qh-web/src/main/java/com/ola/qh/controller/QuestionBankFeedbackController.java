package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.service.IQuestionBankFeedbackService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/feedback")
public class QuestionBankFeedbackController {

	@Autowired
	private IQuestionBankFeedbackService questionBankFeedbackService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<QuestionBankFeedback>> feedbackList(@RequestParam(name="userId",required=true)String userId){
		
		Results<List<QuestionBankFeedback>> results=new Results<List<QuestionBankFeedback>>();
		
		List<QuestionBankFeedback> list=questionBankFeedbackService.feedbackList(userId);
		
		if(list.size()<=0){
			
			results.setStatus("1");
			return results;
		}
		results.setData(list);
		results.setStatus("0");
		return results;
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Results<String> addFeedback(@RequestBody @Valid QuestionBankFeedback questionBankFeedback,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			
			results.setMessage("信息不全，请检查");
			results.setStatus("1");
			return results;
		}
		
		questionBankFeedback.setId(KeyGen.uuid());
		questionBankFeedback.setAddtime(new Date());
		int add=questionBankFeedbackService.addFeedback(questionBankFeedback);
		
		if(add<=0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setStatus("0");
		return results;
	}
}
