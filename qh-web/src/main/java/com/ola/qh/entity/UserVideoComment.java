package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class UserVideoComment {

	private String id;
	@NotEmpty
	private String userId;
	@NotEmpty
	private String vid;
	
	private String commentid;
	@NotEmpty
	private String comments;
	
	private String likesNumber;
	
	private Date addtime;
	@NotNull
	private int types;///1:评论  2;回复
	
	private String nickname;/////昵称
	
	private String headImgUrl;////头像
	
	private int islike;////是否已经点过赞了
	
	private String showtime;////展示时间
	
	private List<UserVideoComment> replylist=new ArrayList<UserVideoComment>();

	
	public int getIslike() {
		return islike;
	}

	public void setIslike(int islike) {
		this.islike = islike;
	}

	public List<UserVideoComment> getReplylist() {
		return replylist;
	}

	public void setReplylist(List<UserVideoComment> replylist) {
		this.replylist = replylist;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLikesNumber() {
		return likesNumber;
	}

	public void setLikesNumber(String likesNumber) {
		this.likesNumber = likesNumber;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
	
}
