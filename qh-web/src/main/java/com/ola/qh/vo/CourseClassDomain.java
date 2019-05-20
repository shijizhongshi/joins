package com.ola.qh.vo;

public class CourseClassDomain {

	private String courseTypeName;

	private String courseTypeSubclassName;

	private int pageNo;

	private int pageSize;

	private int isremmend;//// 是不是推荐的课程

	private int page;

	private String classId;/// 班级的id

	private String teacherName;

	private String className;

	private String maxdoudou;

	private String liveId;// 直播id

	private String liveRoomId;// 直播间id

	private String liveBackId;// 直播回放id

	private String live;

	private Integer status;

	private Integer isMark;// 是否预约 0：未预约 1：已预约

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsMark() {
		return isMark;
	}

	public void setIsMark(Integer isMark) {
		this.isMark = isMark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getLiveRoomId() {
		return liveRoomId;
	}

	public void setLiveRoomId(String liveRoomId) {
		this.liveRoomId = liveRoomId;
	}

	public String getLiveBackId() {
		return liveBackId;
	}

	public void setLiveBackId(String liveBackId) {
		this.liveBackId = liveBackId;
	}

	public String getMaxdoudou() {
		return maxdoudou;
	}

	public void setMaxdoudou(String maxdoudou) {
		this.maxdoudou = maxdoudou;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

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
