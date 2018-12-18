package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class UserCourseHistory {

	private String id;
	
	@NotEmpty(message="用户id不能为空")
	private String userId;
	
	@NotEmpty(message="班级id不能为空")
	private String classId;
	
	@NotEmpty(message="班级名称不能为空")
	private String className;
	
	@NotEmpty(message="班级图片不能为空")
	private String classImgUrl;
	
	private Date addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassImgUrl() {
		return classImgUrl;
	}

	public void setClassImgUrl(String classImgUrl) {
		this.classImgUrl = classImgUrl;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
