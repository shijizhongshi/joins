package com.ola.qh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IQuestionBankUserFinishService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserFinishDomain;

@RestController
@RequestMapping(value="/api/QuestionBankUserFinish")
public class QuestionBankUserFinishController {

	@Autowired
	private IQuestionBankUserFinishService questionBankUserFinishService;
	
	@RequestMapping(value="/addupdate",method=RequestMethod.POST)
	public Results<String> addupdateUserFinish(@RequestBody @Valid UserFinishDomain userFinishDomain,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			
			results.setStatus("1");
			results.setMessage("信息不足");
			return results;
		}
		
		return questionBankUserFinishService.addupdateUserFinish(userFinishDomain);
	}
}
