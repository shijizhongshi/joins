package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;
import com.ola.qh.vo.CourseClassVo;

public interface ICourseClassService {

	public List<CourseNofree> nofreeList(CourseClassDomain ccd);
	
	public CourseNofree nofreeSingle(String id);
	
	public List<CourseClass> classList(CourseClassDomain ccd);
	
	public Results<CourseClassVo> classSingle(String classId);
}
