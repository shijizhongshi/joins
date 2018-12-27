package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class DoctorPatient {

	private String id;
	
	@NotEmpty
	private String userId;
	@NotEmpty
	private String title;
	
	private String describes;
	
	private String ages;
	
	private String sexs;
	
	private String times;
	
	private int issolve;
	
	private String offices;
	
	public List<DoctorPatientImg> imglist=new ArrayList<DoctorPatientImg>();
	
	private Date addtime;
	
	private Date updatetime;
	
	private String publisher;////发布人的信息
	
	public int readStatus;////读的状态1:患者已读    2:医生已读   3:都读了
	
	public int browseCount;/////浏览次数
	
	public String getOffices() {
		return offices;
	}

	public void setOffices(String offices) {
		this.offices = offices;
	}

	public int getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getAges() {
		return ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}

	public String getSexs() {
		return sexs;
	}

	public void setSexs(String sexs) {
		this.sexs = sexs;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getIssolve() {
		return issolve;
	}

	public void setIssolve(int issolve) {
		this.issolve = issolve;
	}

	public List<DoctorPatientImg> getImglist() {
		return imglist;
	}

	public void setImglist(List<DoctorPatientImg> imglist) {
		this.imglist = imglist;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}


	
}
