package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

public class SearchCircleVo {

	private List<News> newsList=new ArrayList<News>(); 
	
	private List<JobFair> jobList=new ArrayList<JobFair>(); 
	
	private List<DoctorPatient> paList=new ArrayList<DoctorPatient>();

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<JobFair> getJobList() {
		return jobList;
	}

	public void setJobList(List<JobFair> jobList) {
		this.jobList = jobList;
	}

	public List<DoctorPatient> getPaList() {
		return paList;
	}

	public void setPaList(List<DoctorPatient> paList) {
		this.paList = paList;
	} 
	
	
}
