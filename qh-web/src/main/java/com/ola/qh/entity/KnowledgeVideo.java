package com.ola.qh.entity;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;

public class KnowledgeVideo {

	private String id;
	@NotEmpty
	private String firstImage;
	@NotEmpty
	private String title;
	@NotEmpty
	private String videoId;

	private String times;

	private String courseTypeSubclassName;

	private String miniSubclassName;

	private int orders;

	private Date addtime;

	private Date updatetime;

	private String logos;// 加盟商logo

	private String mobile;// 加盟商手机号

	public String getMiniSubclassName() {
		return miniSubclassName;
	}

	public void setMiniSubclassName(String miniSubclassName) {
		this.miniSubclassName = miniSubclassName;
	}

	public String getLogos() {
		return logos;
	}

	public void setLogos(String logos) {
		this.logos = logos;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
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

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

}
