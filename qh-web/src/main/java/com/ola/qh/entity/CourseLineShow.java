package com.ola.qh.entity;

import java.util.Date;

public class CourseLineShow {
	private String id;
	private String courseTypeName;
	private String courseTypeSubclassName;
	private String liveName;
	private String imgUrl;
	private String outLinks;/////直播跳转的连接
	

	private String isremmend;////1是推荐
	private String isshow;///1:可见
	private String liveRoomId;//直播间id
	private String liveId;//直播id
	private String liveBackId;//回放id
	
	private int status;////0:未开始直播   1:直播开始  2:直播结束  3:离线回放
	
	private Date addtime;
	
	private Date endtime;
	
	private Date updatetime;


	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getLiveRoomId() {
		return liveRoomId;
	}

	public void setLiveRoomId(String liveRoomId) {
		this.liveRoomId = liveRoomId;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getLiveBackId() {
		return liveBackId;
	}

	public void setLiveBackId(String liveBackId) {
		this.liveBackId = liveBackId;
	}

	public String getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(String outLinks) {
		this.outLinks = outLinks;
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

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIsremmend() {
		return isremmend;
	}

	public void setIsremmend(String isremmend) {
		this.isremmend = isremmend;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
	
}
