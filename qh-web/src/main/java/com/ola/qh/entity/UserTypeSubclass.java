package com.ola.qh.entity;

import java.util.Date;

public class UserTypeSubclass {

	private String id;

	private String userId;

	private String courseTypeSubclassId;

	private String courseTypeSubclassName;

	private Date addTime;

	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourseTypeSubclassId() {
		return courseTypeSubclassId;
	}

	public void setCourseTypeSubclassId(String courseTypeSubclassId) {
		this.courseTypeSubclassId = courseTypeSubclassId;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
