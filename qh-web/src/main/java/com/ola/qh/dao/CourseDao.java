package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineShow;
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
	
	public Integer sectionCount(
			@Param("courseId")String courseId,
			@Param("lecturer")String lecturer);
	
	
	
	public List<CourseLineShow> selectLiveList(
			@Param("courseTypeName")String courseTypeName,
			@Param("courseTypeSubclassName")String courseTypeSubclassName,
			@Param("isremmend")String isremmend,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
}
