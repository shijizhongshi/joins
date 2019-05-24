package com.ola.qh.entity;

import java.util.Date;

public class UserFinishDays {

	private String id;
	
	private String userId;
	
	private String courseTypeSubclassName;
	
	private int userFinsihCount;////做题数
	
	private int trueUserFinsihCount;////正确数
	
	private int days;////坚持天数
	
	private Date addtime;
	
	private Date updatetime;

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

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public int getUserFinsihCount() {
		return userFinsihCount;
	}

	public void setUserFinsihCount(int userFinsihCount) {
		this.userFinsihCount = userFinsihCount;
	}

	public int getTrueUserFinsihCount() {
		return trueUserFinsihCount;
	}

	public void setTrueUserFinsihCount(int trueUserFinsihCount) {
		this.trueUserFinsihCount = trueUserFinsihCount;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
