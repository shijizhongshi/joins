package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.vo.DoctorAndPatient;

public class TopicSquare {

	
	private String id;
	
	private String title;
	
	private String imgUrl;
	
	private String content;
	
	private List<DoctorPatient> list=new ArrayList<DoctorPatient>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<DoctorPatient> getList() {
		return list;
	}

	public void setList(List<DoctorPatient> list) {
		this.list = list;
	}
	
	
}
