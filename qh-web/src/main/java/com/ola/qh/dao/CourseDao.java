package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.vo.CourseClassDomain;
public interface CourseDao {

	public List<CourseType> courseTypeList();
	
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId);
	
	public List<Course> courseList(CourseClassDomain ccd);///xml
	
	public List<CourseChapter> courseChapterList(String courseId);
	
	public List<CourseSection> courseSectionList(String courseChapterId);
	
	public Course singleCourse(String courseId);
	
	public int sectionCount(String courseId);
}
