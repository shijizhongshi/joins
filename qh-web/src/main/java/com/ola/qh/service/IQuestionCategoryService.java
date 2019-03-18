package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.util.Results;

public interface IQuestionCategoryService {
	
	public Results<List<QuestionCategory>> selectCategory(int pageNo,int pageSize,String courseTypeSubclassName,String types);
	
}
