package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.PromptMessage;
import com.ola.qh.service.IPromptMessageService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/promptmessage")
public class PromptMessageController {

	@Autowired
	private IPromptMessageService promptMessageService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<PromptMessage>> selectPatient(@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="issolve",required=true)int issolve){
		
		Results<List<PromptMessage>> results=new Results<List<PromptMessage>>();
		
		List<PromptMessage> list=promptMessageService.selectPatient(userId, issolve);
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<PromptMessage>> selectDoctorReplyPatient(@RequestParam(name="id",required=true)String id){
		
		Results<List<PromptMessage>> results=new Results<List<PromptMessage>>();
		
		List<PromptMessage> list=promptMessageService.selectDoctorReplyPatient(id);
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
