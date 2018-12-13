package com.ola.qh.entity;

import java.util.Date;

public class News {

	private String id;
	
	private String types;
	
	private String title;///标题
	
	private String imgUrl;///标题图片
	
	private String content;///内容
	
	private Date addtime;
	
	private int isstick;////是否置顶
	
	private int status;///1:失效
	
	private String showtime;
	
	private int isFavorite;///1:已经收藏过了   0:没有收藏
	

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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getIsstick() {
		return isstick;
	}

	public void setIsstick(int isstick) {
		this.isstick = isstick;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}
	
	
}
