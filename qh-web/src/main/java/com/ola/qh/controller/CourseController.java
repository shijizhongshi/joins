package com.ola.qh.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
* @ClassName: CourseController  
* @Description: 课程类别和课程集合的列表
* @author guoyuxue  
* @date 2018年11月19日  
*
 */

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.IUserFavoriteService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;
import com.ola.qh.vo.CourseClassVo;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;
	

	/**
	 * 大类别的集合
	 * <p>Title: listCourseType</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping("/courseTypeList")
	public Results<List<CourseType>> listCourseType() {

		Results<List<CourseType>> result = new Results<List<CourseType>>();
		List<CourseType> list = courseService.courseTypeList();
		result.setData(list);
		result.setStatus("0");
		return result;

	}

	/**
	 * 子类别的集合
	 * <p>Title: listCourseTypeSubclass</p>  
	 * <p>Description: </p>  
	 * @param courseTypeId
	 * @return
	 */
	@RequestMapping("/courseTypeSubclassList")
	public Results<List<CourseTypeSubclass>> listCourseTypeSubclass(
			@RequestParam(name = "courseTypeId", required = true) String courseTypeId) {

		Results<List<CourseTypeSubclass>> result = new Results<List<CourseTypeSubclass>>();
		List<CourseTypeSubclass> list = courseService.courseTypeSubclassList(courseTypeId);
		result.setData(list);
		result.setStatus("0");
		return result;

	}

	/**
	 * 
	 * <p>
	 * Title: listCourse
	 * </p>
	 * <p>
	 * 多条件的查询,是否精品课程,按照大类别.小类别查询课程
	 * </p>
	 * 首页传精品课程page=1 其他的情况则是page是变量
	 * 
	 * @param page
	 * @param courseTypeName
	 * @param courseTypeSubclassName
	 * @param courseExcellent
	 * @return
	 */
	@RequestMapping(value="/courseList",method=RequestMethod.POST)
	public Results<List<Course>> listCourse(@RequestBody CourseClassDomain ccd) {

		Results<List<Course>> result = new Results<List<Course>>();

		int pageNo = (ccd.getPage() - 1) * Patterns.zupageSize;
		ccd.setPageNo(pageNo);
		ccd.setPageSize(Patterns.zupageSize);
		result.setData(courseService.courseList(ccd));
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping("/single")
	public Results<Course> singleCourse(
			@RequestParam(name="courseId",required=true)String courseId,
			@RequestParam(name="userId",required=false)String userId){
		
		return courseService.singleCourse(courseId,userId);
		
	}
	
	
	
	/**
	 * 通过课程的id查对应的章的id
	 * <p>Title: ListCourseChapter</p>  
	 * <p>Description: </p>  
	 * @param courseId
	 * @return
	 */
	@RequestMapping("/courseChapterList")
	public Results<List<CourseChapter>> ListCourseChapter(@RequestParam(name="courseId",required=true)String courseId){
		
		Results<List<CourseChapter>> result = new Results<List<CourseChapter>>();
		
		result.setData(courseService.courseChapterList(courseId));
		result.setStatus("0");
		return result;
	}
	
	/**
	 * 通过章的id查出对应的节的列表
	 * <p>Title: listCourseSection</p>  
	 * <p>Description: </p>  
	 * @param courseChapterId
	 * @return
	 */
	@RequestMapping("/courseSectionList")
	public Results<List<CourseSection>> listCourseSection(@RequestParam(name="courseChapterId",required=true)String courseChapterId){
		
		Results<List<CourseSection>> result = new Results<List<CourseSection>>();
		
		result.setData(courseService.courseSectionList(courseChapterId));
		result.setStatus("0");
		return result;
	}
	
	
	@RequestMapping("/lineShow")
	public Results<List<CourseLineShow>> selectLiveList(
			@RequestParam(name="courseTypeName")String courseTypeName,
			@RequestParam(name="courseTypeSubclassName")String courseTypeSubclassName,
			@RequestParam(name="isremmend")String isremmend,
			@RequestParam(name="page")int page
			){
		Results<List<CourseLineShow>> result=new Results<List<CourseLineShow>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<CourseLineShow> list = courseService.selectLiveList(courseTypeName, courseTypeSubclassName, isremmend, pageNo, pageSize);
		result.setData(list);
		result.setStatus("0");
		return result;
	}

}
