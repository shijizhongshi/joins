package com.ola.qh.entity;

import java.util.Date;

public class CourseLineShow {
	private String id;
	private String courseTypeName;
	private String courseTypeSubclassName;
	private String liveName;
	private String imgUrl;
	private String outLinks;///// 直播跳转的连接

	private String isremmend;//// 1是推荐
	private String isshow;/// 1:可见
	private String liveRoomId;// 直播间id
	private String liveId;// 直播id
	private String liveBackId;// 回放id

	private int status;//// 0:未开始直播 1:直播开始 2:直播结束 3:离线回放

	private Date starttime;// 手动输入的直播开始时间

	private Date stoptime;// 手动输入的直播结束时间

	private Date addtime;// 精确的直播开始时间

	private Date endtime;// 精确的直播结束时间

	private Date updatetime;

	private String offlineUrl;



	private String date;// 格式化后的日期
	private String startToEnd;// 格式化后的开始结束时间

	private String lecturer; // 主讲老师

	private Integer isMark;// 状态值 是否预约
	
	private String headImgUrl;//老师头像图片
	
	private Integer isopen;// 是否是公开课 0:不公开 1:公开
	
	private String playBackVideoId;///////////////回放的id

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getStoptime() {
		return stoptime;
	}

	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}

	public Integer getIsMark() {
		return isMark;
	}

	public void setIsMark(Integer isMark) {
		this.isMark = isMark;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartToEnd() {
		return startToEnd;
	}

	public void setStartToEnd(String startToEnd) {
		this.startToEnd = startToEnd;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public String getOfflineUrl() {
		return offlineUrl;
	}

	public void setOfflineUrl(String offlineUrl) {
		this.offlineUrl = offlineUrl;
	}

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

	public String getPlayBackVideoId() {
		return playBackVideoId;
	}

	public void setPlayBackVideoId(String playBackVideoId) {
		this.playBackVideoId = playBackVideoId;
	}

}
