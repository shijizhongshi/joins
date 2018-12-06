package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;

public interface ICourseService {

	public List<CourseType> courseTypeList();
	
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId);
	
	public List<Course> courseList(Course course);
	
	public Course singleCourse(String courseId);
	
	public List<CourseChapter> courseChapterList(String courseId);
	
	public List<CourseSection> courseSectionList(String courseChapterId);
}
