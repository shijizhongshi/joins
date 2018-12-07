package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class UserComment {

	private String id;
	
	@NotEmpty(message="店铺id不能为空")
	private String shopId;
	
	@NotEmpty(message="用户d不能为空")
	private String userId;
	
	@NotEmpty(message="评论内容不能为空")
	private String comment;
	
	private String commentType;
	
	private Date addtime;
	
	private List<UserCommentImg> list=new ArrayList<UserCommentImg>();

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
	
	
}
