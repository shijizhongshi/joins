package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.vo.DoctorAndPatient;

public class TopicSquare {

	
	private String id;
	
	private String title;
	
	private String content;
	
	private List<DoctorAndPatient> list=new ArrayList<DoctorAndPatient>();

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

	public List<DoctorAndPatient> getList() {
		return list;
	}

	public void setList(List<DoctorAndPatient> list) {
		this.list = list;
	}
	
	
}
