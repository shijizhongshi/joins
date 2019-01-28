package com.ola.qh.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Reply {

	private String id;
	
	@NotEmpty
	private String userId;
	@NotEmpty
	private String patientId;
	@NotEmpty
	private String replyContent;
	
	private Date addtime;

	private int likes;////点赞个数
	
	@NotNull
	private int type;///1:评论 2;回复
	
	private String showtime;

	public String replyName;
	
	private String replyHeadImg;
	
	private int islikes;///1:点过赞   0:没有
	
	

	public int getIslikes() {
		return islikes;
	}

	public void setIslikes(int islikes) {
		this.islikes = islikes;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getReplyHeadImg() {
		return replyHeadImg;
	}

	public void setReplyHeadImg(String replyHeadImg) {
		this.replyHeadImg = replyHeadImg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
}
