package com.ola.qh.entity;

import java.util.Date;

public class QuestionAnswer {

	private String id;
	
	private String bankUnitId;
	
	private String answers;
	
	private String options;/////A B类似于这种的
	
	private Date addtime;
	
	private Date updatetime;
	
	private boolean correct;///true:正确   FALSE :错误
	
	private int orders;
	
	private String titleimg;
	
	
	
	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankUnitId() {
		return bankUnitId;
	}

	public void setBankUnitId(String bankUnitId) {
		this.bankUnitId = bankUnitId;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
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
	
	
}
