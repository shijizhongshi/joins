package com.ola.qh.controller;

import java.text.SimpleDateFormat;
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
import com.ola.qh.entity.User;
import com.ola.qh.service.IQuestionBankFeedbackService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/feedback")
public class QuestionBankFeedbackController {

	@Autowired
	private IQuestionBankFeedbackService questionBankFeedbackService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<QuestionBankFeedback>> feedbackList(@RequestParam(name="userId",required=true)String userId){
		
		
		Results<List<QuestionBankFeedback>> results=new Results<List<QuestionBankFeedback>>();
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		
		
		List<QuestionBankFeedback> list=questionBankFeedbackService.feedbackList(userResult.getData().getId());
		
		for (QuestionBankFeedback questionBankFeedback : list) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			questionBankFeedback.setShowtime(sf.format(questionBankFeedback.getAddtime()));
		}
		
		
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
		Results<User> userResult = userService.existUser(questionBankFeedback.getUserId());
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		questionBankFeedback.setUserId(userResult.getData().getId());
		
		
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
