package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionSubcategoryService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/questionsubcategory")
public class QuestionSubcategoryController {

	@Autowired
	private IQuestionSubcategoryService questionSubCategoryService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(@RequestParam(name="pageNo",required=true)int pageNo
			,@RequestParam(name="pageSize",required=true)int pageSize,@RequestParam(name="categoryId",required=true)String categoryId){
		
		return questionSubCategoryService.selectQuestionSubCategory(pageNo, pageSize, categoryId);
		
	}
	
}
