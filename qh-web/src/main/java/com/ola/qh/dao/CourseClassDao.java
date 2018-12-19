package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.vo.CourseClassDomain;

public interface CourseClassDao {

	public List<CourseNofree> nofreeList(CourseClassDomain ccd);
		
		public CourseNofree nofreeSingle(String id);
		
		public List<CourseClass> classList(CourseClassDomain ccd);
		
		public CourseClass classSingle(String id);
		
		public List<CourseTeacher> teacherList(@Param("classId")String classId);/////根据课程的id查老师的集合
		
		public int teacherCount(@Param("classId") String classId);////老师的总个数
		
		public int ordersCount(String productId);/////查课程订单的个数
}
