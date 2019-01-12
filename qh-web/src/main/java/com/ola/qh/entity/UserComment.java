package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class UserComment {

	private String id;
	
	private String shopId;
	
	private String doctorId;
	
	@NotEmpty(message="用户d不能为空")
	private String userId;
	
	private String comment;
	
	private String commentType;
	
	private Date addtime;
	
	private int grade;//用户对商品的评分
	
	private String textName;//评论文本
	
	private List<UserCommentImg> list=new ArrayList<UserCommentImg>();
	
	private List<String> textlist=new ArrayList<String>();
	
	private String showtime;///时间
	
	private String headImgUrl;////用户的头像
	
	private String nickname;////用户的昵称
	
	private String[] textname;//返回的评论文本

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public List<UserCommentImg> getList() {
		return list;
	}

	public void setList(List<UserCommentImg> list) {
		this.list = list;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public List<String> getTextlist() {
		return textlist;
	}

	public void setTextlist(List<String> textlist) {
		this.textlist = textlist;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String[] getTextname() {
		return textname;
	}

	public void setTextname(String[] textname) {
		this.textname = textname;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	
}
