package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.vo.CourseClassDomain;
public interface CourseDao {

	public List<CourseType> courseTypeList(@Param("id")String id);
		
	public List<CourseTypeSubclass> courseTypeSubclassList(@Param("courseTypeId") String courseTypeId);
	
	public List<Course> courseList(CourseClassDomain ccd);///xml
	
	public List<CourseChapter> courseChapterList(@Param("courseId")String courseId);
	
	public List<CourseSection> courseSectionList(@Param("courseChapterId")String courseChapterId);
	
	public Course singleCourse(@Param("courseId")String courseId);
	
	public int courseChapterCount(@Param("courseId")String courseId);
	
	public int courseSectionCount(@Param("chapterId")String chapterId);
	
	public List<CourseLineShow> selectLiveList(CourseClassDomain ccd);
}
