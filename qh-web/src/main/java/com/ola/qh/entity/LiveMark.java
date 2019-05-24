package com.ola.qh.entity;

import java.util.Date;

public class LiveMark {

	private String id;

	private String userId;// 用户ID

	private String liveName;// 直播名

	private String liveId;// 直播ID

	private Integer status;// 是否推送过信息 0：没推送过 1：推送过

	private Date starttime;// 直播开始时间

	private Integer isMark;// 预约状态 0：未预约 1：预约

	public Integer getIsMark() {
		return isMark;
	}

	public void setIsMark(Integer isMark) {
		this.isMark = isMark;
	}

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

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
}
