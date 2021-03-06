package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @ClassName: UserBuyCourse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guoyuxue
 * @date 2018年11月29日
 *
 */
public class UserBuyCourse {

	private String userId;/// 用户的id

	private String id;

	private String courseId;

	private String classId;

	private String courseName;////

	private BigDecimal courseDiscountPrice;//// 课程的折扣价

	private BigDecimal coursePrice;//// 课程的原价

	private String courseImgUrl;//// 课程的图片

	private String ordersId;

	private String showtime;

	private Date addtime;///

	private String payType;/// 线上支付或者是线下支付的

	private List<Course> listcourse = new ArrayList<Course>();

	private Integer classChapterSize;

	public Integer getClassChapterSize() {
		return classChapterSize;
	}

	public void setClassChapterSize(Integer classChapterSize) {
		this.classChapterSize = classChapterSize;
	}

	public List<Course> getListcourse() {
		return listcourse;
	}

	public void setListcourse(List<Course> listcourse) {
		this.listcourse = listcourse;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigDecimal getCourseDiscountPrice() {
		return courseDiscountPrice;
	}

	public void setCourseDiscountPrice(BigDecimal courseDiscountPrice) {
		this.courseDiscountPrice = courseDiscountPrice;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCourseImgUrl() {
		return courseImgUrl;
	}

	public void setCourseImgUrl(String courseImgUrl) {
		this.courseImgUrl = courseImgUrl;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

}
