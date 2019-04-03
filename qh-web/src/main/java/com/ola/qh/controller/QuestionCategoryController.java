package com.ola.qh.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/questionCategory")
public class QuestionCategoryController {

	@Autowired
	private IQuestionCategoryService questionCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<QuestionCategory>> selectCategory(@RequestParam(name="page",required=true)int page,
			@RequestParam(name="courseTypeSubclassName",required=true)String courseTypeSubclassName,
			@RequestParam(name="types",required=true)String types){
		
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		return questionCategoryService.selectCategory(pageNo,pageSize,courseTypeSubclassName,types);
	}
	
	
}
