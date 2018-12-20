package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Banner;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.LiveShow;

public class ClassVo {

	private int days;///还剩多少天考试
	
	private List<Banner> bannerlist=new ArrayList<Banner>();///bannner集合
	
	private List<CourseNofree> nofreelist=new ArrayList<CourseNofree>();///免费课程
	
	private List<CourseClass> courseClass=new ArrayList<CourseClass>();///推荐课程
	
	private List<LiveShow> livelist=new ArrayList<LiveShow>();////直播的集合

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<Banner> getBannerlist() {
		return bannerlist;
	}

	public void setBannerlist(List<Banner> bannerlist) {
		this.bannerlist = bannerlist;
	}

	public List<CourseNofree> getNofreelist() {
		return nofreelist;
	}

	public void setNofreelist(List<CourseNofree> nofreelist) {
		this.nofreelist = nofreelist;
	}

	public List<CourseClass> getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(List<CourseClass> courseClass) {
		this.courseClass = courseClass;
	}

	public List<LiveShow> getLivelist() {
		return livelist;
	}

	public void setLivelist(List<LiveShow> livelist) {
		this.livelist = livelist;
	}
	
	
}
