package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionBank {

	private String  id;
	
	private String subId;/////属于哪个下边的题库
	
	private int numberNo;///题号
	
	private String title;///题干
	
	private String[] titles;///题干
	
	private String types;////问题的类型(单选)
	
	private String analysis;///问题的解析
	
	private String correct;///正确的答案
	
	private String showtime;///正确的答案
	
	private Date addtime;
	
	private Date updatetime;
	
	private int status;//////////用户做过传1，没做过传0
	
	private String bankId;/////共用题干类型的题 小单元题
	 
	private List<QuestionAnswer> answer=new ArrayList<QuestionAnswer>();
	
	private QuestionUnitTypes unit=new QuestionUnitTypes();
	
	private String titleimg;
	
	
	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public List<QuestionAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<QuestionAnswer> answer) {
		this.answer = answer;
	}

	public QuestionUnitTypes getUnit() {
		return unit;
	}

	public void setUnit(QuestionUnitTypes unit) {
		this.unit = unit;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
