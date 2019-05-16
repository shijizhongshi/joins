package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionBankUserFinish {

	
	private String id;
	
	private String userId;
	@NotEmpty
	private String bankId;////////////试题id
	@NotEmpty
	private String subId;/////////节或单元id
	
	private int status;/////////是否答对或未看解析
	
	private int types;/////////
	
	@NotEmpty
	private String numberNo;/////////题目序号
	
	private Date addtime;
	
	private Date updatetime;
	
	private int conditions;/////////1表示为共用题干或共用选项0表示为单选或多选

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

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNumberNo() {
		return numberNo;
	}

	public void setNumberNo(String numberNo) {
		this.numberNo = numberNo;
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

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public int getConditions() {
		return conditions;
	}

	public void setConditions(int conditions) {
		this.conditions = conditions;
	}

	

}
