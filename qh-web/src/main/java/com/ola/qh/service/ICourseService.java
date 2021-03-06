package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineCCresult;
import com.ola.qh.entity.CourseLineCheck;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;

public interface ICourseService {

	public List<CourseType> courseTypeList();
	
	public List<CourseType> courseTypeSubclassList(String courseTypeId);
	
	public List<Course> courseList(CourseClassDomain courseList);
	
	public  Results<Course> singleCourse(String courseId,String userId);
	
	public List<CourseChapter> courseChapterList(String courseId);
	
	public List<CourseSection> courseSectionList(String courseChapterId);
	
	public List<CourseLineShow> selectLiveList(CourseClassDomain ccd);
	
	public CourseLineShow singleLiveShow(String roomId,String liveId);
	
	public int updateListShow(CourseLineShow cls);
	
	public int insertCCresult(CourseLineCCresult ccresult);
	
	
	public int insertLineCheck(CourseLineCheck clc);
	
	public int updateLineCheck(String courseTypeSubclassName,String roomid,Date updatetime,String id);
	
	public CourseLineCheck singleLineCheck(String mobile);

	public Results<List<CourseTypeSubclassNames>> selectThree(String courseTypeSubclassId);

	public List<CourseLineWhite> selectAllByLiveId(String liveId);
	
	public Results<Integer> acquire(String lineShowId, String userId);

	public List<CourseChapter> courseChapterListAll(String courseId);

}
