package com.ola.qh.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionBankUserFinish;
import com.ola.qh.service.IQuestionBankUserFinishService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserFinishDomain;

@RestController
@RequestMapping(value="/api/QuestionBankUserFinish")
public class QuestionBankUserFinishController {

	@Autowired
	private IQuestionBankUserFinishService questionBankUserFinishService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Results<String> addUserFinish(@RequestBody @Valid UserFinishDomain userFinishDomain,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		for (QuestionBankUserFinish questionBankUserFinish : userFinishDomain.getList()) {
			
		
		questionBankUserFinish.setUserId(userFinishDomain.getUserId());
		questionBankUserFinish.setId(KeyGen.uuid());
		questionBankUserFinish.setAddtime(new Date());
		int insert=questionBankUserFinishService.addUserFinish(questionBankUserFinish);
		
			if(insert<=0){
			
				results.setStatus("1");
				return results;
			}
		
		}
		
		results.setStatus("0");
		return results;
	}
	
	
}
