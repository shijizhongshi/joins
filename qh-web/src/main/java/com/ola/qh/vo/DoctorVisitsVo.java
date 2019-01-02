package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.News;

public class DoctorVisitsVo {

	private List<Doctors> doctor=new ArrayList<Doctors>();
	
	private List<DoctorAndPatient> patient=new ArrayList<DoctorAndPatient>();
	
	private List<News> news=new ArrayList<News>();
	
	private String readStatus;
	
	private int isDoctor;

	public List<Doctors> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctors> doctor) {
		this.doctor = doctor;
	}

	public List<DoctorAndPatient> getPatient() {
		return patient;
	}

	public void setPatient(List<DoctorAndPatient> patient) {
		this.patient = patient;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String isread) {
		this.readStatus = isread;
	}

	public int getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(int isDoctor) {
		this.isDoctor = isDoctor;
	}
	
	
	
	
}
