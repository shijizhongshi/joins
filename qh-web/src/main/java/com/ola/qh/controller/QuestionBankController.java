package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 
import com.ola.qh.entity.QuestionBankTypes;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/questionbank")
public class QuestionBankController {
	
	@Autowired
	private IQuestionBankService questionBankService;
	/**
	 * 题库的上传
	 * <p>
	 * Title: improtExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<QuestionBankTypes> selectQuestionBank(@RequestParam(value = "subId",required=true) String subId,
			@RequestParam(value = "userId",required=false) String userId){
		
		return questionBankService.selectQuestionBank(subId,userId);
	}
	
	
}
