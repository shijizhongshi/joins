package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.vo.CourseClassDomain;

public interface CourseClassDao {

	public List<CourseNofree> nofreeList(CourseClassDomain ccd);

	public CourseNofree nofreeSingle(@Param("id") String id);

	public List<CourseClass> classList(CourseClassDomain ccd);

	public CourseClass classSingle(@Param("id") String id);

	public List<CourseTeacher> teacherList(@Param("classId") String classId,
			@Param("courseLecturer") String courseLecturer);///// 根据课程的id查老师的集合， 并且排除主讲老师

	public List<CourseTeacher> selectCourseLecturer(@Param("classId") String classId,
			@Param("courseLecturer") String courseLecturer);//根据课程id 主讲老师名字查询集合

	public int teacherCount(@Param("classId") String classId);//// 老师的总个数

	public int ordersCount(@Param("productId") String productId);///// 查课程订单的个数

	////// 通过name查教师的信息
	public String getTeacherImg(@Param("name") String name);

}
