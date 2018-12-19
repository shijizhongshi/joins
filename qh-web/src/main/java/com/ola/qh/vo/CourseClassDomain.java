package com.ola.qh.vo;

public class CourseClassDomain {

	private String courseTypeName;
	
	private String courseTypeSubclassName;
	
	private int pageNo;
	
	private int pageSize;
	
	private int isremmend;////是不是推荐的课程
	
	private int page;
	
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(int isremmend) {
		this.isremmend = isremmend;
	}
	
	
	
	
}
