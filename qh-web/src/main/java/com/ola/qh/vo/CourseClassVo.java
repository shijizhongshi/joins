package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseTeacher;

public class CourseClassVo extends CourseClass{

	private List<Course> courselist=new ArrayList<Course>();///课程的集合
	
	private List<CourseTeacher> teacherlist=new ArrayList<>();///教师的集合
	
	private int teacherCount;////教师的总个数
	
	private int buyCount;////购买的人数
	
	private int sectionCount;////总的章节数

	public List<Course> getCourselist() {
		return courselist;
	}

	public void setCourselist(List<Course> courselist) {
		this.courselist = courselist;
	}

	public List<CourseTeacher> getTeacherlist() {
		return teacherlist;
	}

	public void setTeacherlist(List<CourseTeacher> teacherlist) {
		this.teacherlist = teacherlist;
	}

	public int getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getSectionCount() {
		return sectionCount;
	}

	public void setSectionCount(int sectionCount) {
		this.sectionCount = sectionCount;
	}
	
}
