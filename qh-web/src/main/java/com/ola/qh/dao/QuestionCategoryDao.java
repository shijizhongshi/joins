package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionCategory;

public interface QuestionCategoryDao {

	public int countCategory(@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("types")String types);
	
	public QuestionCategory singleCategory(@Param("categoryName")String categoryName,@Param("subclassName")String subclassName);
	
	public Integer existSubCategory(@Param("categoryId")String categoryId,@Param("subName")String subName);
	
	public List<QuestionCategory> selectCategory(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("types")String types);
	
}
