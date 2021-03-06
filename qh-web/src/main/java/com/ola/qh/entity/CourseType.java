package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: CourseType  
* @Description: 课程所有类别的实体类  
* @author guoyuxue  
* @date 2018年11月19日  
*
 */
public class CourseType {
	
	private String id;
	
	private String courseTypeName;////名称
	
	private String imgUrl;//图片地址
	
	private List<CourseTypeSubclass> sublist=new ArrayList<CourseTypeSubclass>();
	
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public List<CourseTypeSubclass> getSublist() {
		return sublist;
	}

	public void setSublist(List<CourseTypeSubclass> sublist) {
		this.sublist = sublist;
	}

	
	

}
