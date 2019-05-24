package com.ola.qh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: Course
 * @Description: 课程的实体类
 * @author guoyuxue
 * @date 2018年11月19日
 *
 */
public class Course {

	private String id;

	private String userId;//// 课程所属者

	private String courseName;// 课程名称

	private String courseTypeName;/// 课程类别的名称

	private String courseTypeSubclassName;//// 课程子类别的名称

	private int courseChapterSize;//// 课程的章节数

	private BigDecimal coursePrice;//// 课程的价格

	private BigDecimal courseDiscountPrice;//// 课程折扣价格

	private int maxdoudou;//// 购买整个课程最多的豆豆数

	private String courseYear;//// 课程年限

	private int courseShow;//// 课程是否展示

	private int courseExcellent;/// 是否是优秀课程

	private String courseResourceType;///// 课程资源类别

	private String courseUseDifference;///// 课程用途类别

	private String courseImg;//// 课程的图片

	private Date addtime;

	private Date updatetime;

	private int isFavorite;/// 1:已经收藏 0:没有收藏

	private String classId;/// 按照班级的ID查

	private int courseStatus;//// 1:已经购买了

	private List<CourseChapter> courseChapters;//章集合

	public List<CourseChapter> getCourseChapters() {
		return courseChapters;
	}

	public void setCourseChapters(List<CourseChapter> courseChapters) {
		this.courseChapters = courseChapters;
	}

	public int getMaxdoudou() {
		return maxdoudou;
	}

	public void setMaxdoudou(int maxdoudou) {
		this.maxdoudou = maxdoudou;
	}

	public int getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public int getCourseChapterSize() {
		return courseChapterSize;
	}

	public void setCourseChapterSize(int courseChapterSize) {
		this.courseChapterSize = courseChapterSize;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	public BigDecimal getCourseDiscountPrice() {
		return courseDiscountPrice;
	}

	public void setCourseDiscountPrice(BigDecimal courseDiscountPrice) {
		this.courseDiscountPrice = courseDiscountPrice;
	}

	public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}

	public int getCourseShow() {
		return courseShow;
	}

	public void setCourseShow(int courseShow) {
		this.courseShow = courseShow;
	}

	public int getCourseExcellent() {
		return courseExcellent;
	}

	public void setCourseExcellent(int courseExcellent) {
		this.courseExcellent = courseExcellent;
	}

	public String getCourseResourceType() {
		return courseResourceType;
	}

	public void setCourseResourceType(String courseResourceType) {
		this.courseResourceType = courseResourceType;
	}

	public String getCourseUseDifference() {
		return courseUseDifference;
	}

	public void setCourseUseDifference(String courseUseDifference) {
		this.courseUseDifference = courseUseDifference;
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

	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
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

}
