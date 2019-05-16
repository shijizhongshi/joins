package com.ola.qh.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineCCresult;
import com.ola.qh.entity.CourseLineCheck;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.entity.LiveMark;
import com.ola.qh.vo.CourseClassDomain;
public interface CourseDao {

	public List<CourseType> courseTypeList(@Param("id")String id);
		
	public List<CourseTypeSubclass> courseTypeSubclassList(@Param("courseTypeId") String courseTypeId);
	
	public List<Course> courseList(CourseClassDomain ccd);///xml
	
	public CourseType singleCourseType(@Param("id") String id);
	
	public CourseTypeSubclass singleCourseTypeSubclass(@Param("courseTypeSubclassName") String courseTypeSubclassName);
	
	public List<CourseChapter> courseChapterList(@Param("courseId")String courseId);
	
	public List<CourseSection> courseSectionList(@Param("courseChapterId")String courseChapterId);
	
	public Course singleCourse(@Param("courseId")String courseId);
	
	public int courseChapterCount(@Param("courseId")String courseId);
	
	public int courseSectionCount(@Param("chapterId")String chapterId);
	
	public int sectionCount(@Param("courseId")String courseId,@Param("lecturer")String lecturer);
	
	public List<CourseLineShow> selectLiveList(CourseClassDomain ccd);
	
	public CourseLineShow singleLiveShow(@Param("roomId")String roomId);
	
	public int updateListShow(CourseLineShow cls);
	
	public int insertCCresult(CourseLineCCresult ccresult);
	
	
	public int insertLineCheck(CourseLineCheck clc);
	
	public int updateLineCheck(
			@Param("courseTypeSubclassName")String courseTypeSubclassName,
			@Param("roomid")String roomid,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
	
	public CourseLineCheck singleLineCheck(@Param("mobile")String mobile);

	public List<CourseTypeSubclassNames> select(@Param("courseTypeSubclassId")String courseTypeSubclassId);
	
	
	public List<CourseLineWhite> selectAllByLiveId(@Param("liveId")String liveId);

	public Integer selectMiniByTypeSubclassId(@Param("courseTypeSubclassId")String id);

	public CourseLineShow selectById(@Param("lineShowId")String lineShowId);

	public Integer insertLiveMark(LiveMark liveMark);

	public List<LiveMark> selectStartTime(@Param("date")String date);

	public Integer updateStatus(@Param("userId")String userId);

	public List<Date> selectLiveShow(@Param("date")String date);

	
}
