package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionSubCategory;

public interface QuestionSubcategoryDao {

	public List<QuestionSubCategory> selectQuestionSubCategory(@Param("categoryId")String categoryId);
	
	public int countSubCategory(@Param("categoryId")String categoryId);
	
}
