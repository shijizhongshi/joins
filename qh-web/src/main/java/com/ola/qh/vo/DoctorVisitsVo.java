package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.News;

public class DoctorVisitsVo {

	private List<Doctors> doctor=new ArrayList<Doctors>();
	
	private List<DoctorPatient> patient=new ArrayList<DoctorPatient>();
	
	private List<News> news=new ArrayList<News>();

	public List<Doctors> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctors> doctor) {
		this.doctor = doctor;
	}

	public List<DoctorPatient> getPatient() {
		return patient;
	}

	public void setPatient(List<DoctorPatient> patient) {
		this.patient = patient;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
	
	
	
	
}
