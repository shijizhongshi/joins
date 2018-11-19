package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.CourseType;
public interface CourseDao {

	public List<CourseType> CourseTypeList();
	
	public List<String> CourseTypeSubclassList(String courseTypeId);
}
