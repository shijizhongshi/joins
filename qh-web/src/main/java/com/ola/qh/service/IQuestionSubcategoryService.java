package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.util.Results;

public interface IQuestionSubcategoryService {

	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(String categoryId,String userId);
}
