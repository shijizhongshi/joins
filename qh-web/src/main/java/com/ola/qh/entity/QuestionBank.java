package com.ola.qh.entity;

import java.util.Date;

public class QuestionBank {

	private String id;
	
	private String subId;
	
	private int numberNo;
	
	private String title;
	
	private String types;
	
	private String analysis;
	
	private String correct;
	
	private Date addtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public int getNumberNo() {
		return numberNo;
	}

	public void setNumberNo(int numberNo) {
		this.numberNo = numberNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
