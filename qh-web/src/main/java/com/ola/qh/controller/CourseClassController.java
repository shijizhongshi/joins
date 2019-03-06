package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.service.imp.CourseClassService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;
import com.ola.qh.vo.CourseClassVo;
/**
 * 班级课程的查询
* @ClassName: CourseClassController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年12月19日  
*
 */
@RestController
@RequestMapping("/api/courseclass")
public class CourseClassController {

	@Autowired
	private ICourseClassService courseClassService;
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public Results<List<CourseClass>> classList(@RequestBody CourseClassDomain ccd){
		Results<List<CourseClass>> result=new Results<List<CourseClass>>();
		List<CourseClass> list = courseClassService.classList(ccd);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping("/single")
	public Results<CourseClassVo> single(@RequestParam(name="classId",required=true)String classId,
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="address",required=false)String address){
		return courseClassService.classSingle(classId,userId,address);
		
	}	
	////////免费课程的开始////////////////////////////
	@RequestMapping(value="/nofreelist",method=RequestMethod.POST)
	public Results<List<CourseNofree>> nofreeList(@RequestBody CourseClassDomain ccd){
		Results<List<CourseNofree>> result=new Results<List<CourseNofree>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(ccd.getPage()-1)*pageSize;
		ccd.setPageNo(pageNo);
		ccd.setPageSize(pageSize);
		List<CourseNofree> list = courseClassService.nofreeList(ccd);
		result.setStatus("0");
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/nofreesingle")
	public Results<CourseNofree> nofreeSingle(@RequestParam(name="id",required=true)String id,
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="address",required=false)String address){
		Results<CourseNofree> result=new Results<CourseNofree>();
		result.setData(courseClassService.nofreeSingle(id, address, userId));
		result.setStatus("0");
		return result;
	}
	////////免费课程的结束////////////////////////////
}
