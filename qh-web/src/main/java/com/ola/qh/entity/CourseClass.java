package com.ola.qh.entity;

import java.math.BigDecimal;

public class CourseClass {

	private String id;
	
	private String courseTypeName;///班级专业
	
	private String courseTypeSubclassName;///班级子专业
	
	private String className;//班级名称
	
	private BigDecimal classPrice;//原价
	
	private BigDecimal classDiscountPrice;///折扣价
	
	private String courseLecturer;///主讲人
	
	private String imgUrl;///班级图片
	
	private String properPeople;///适应人群
	
	private String features;////班级的特色
	
	private String promises;////班级承诺
	
	private String introduce;////介绍
	
	private String detail;///详情
	
	private int maxdoudou;////购买整个课程最多的豆豆数
	
	private String tearcherImg;
	
	private int buyCount;/////购买的人数
	
	
	


	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public String getTearcherImg() {
		return tearcherImg;
	}

	public void setTearcherImg(String tearcherImg) {
		this.tearcherImg = tearcherImg;
	}

	public int getMaxdoudou() {
		return maxdoudou;
	}

	public void setMaxdoudou(int maxdoudou) {
		this.maxdoudou = maxdoudou;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public BigDecimal getClassPrice() {
		return classPrice;
	}

	public void setClassPrice(BigDecimal classPrice) {
		this.classPrice = classPrice;
	}

	public BigDecimal getClassDiscountPrice() {
		return classDiscountPrice;
	}

	public void setClassDiscountPrice(BigDecimal classDiscountPrice) {
		this.classDiscountPrice = classDiscountPrice;
	}

	public String getCourseLecturer() {
		return courseLecturer;
	}

	public void setCourseLecturer(String courseLecturer) {
		this.courseLecturer = courseLecturer;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProperPeople() {
		return properPeople;
	}

	public void setProperPeople(String properPeople) {
		this.properPeople = properPeople;
	}

	public String getPromises() {
		return promises;
	}

	public void setPromises(String promises) {
		this.promises = promises;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
}
