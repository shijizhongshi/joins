package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionBankUserFinish {

	
	private String id;
	
	private String userId;
	@NotEmpty
	private String bankId;////////////试题序号
	@NotEmpty
	private String istrue;/////////是否答对
	@NotEmpty
	private String numberNo;/////////题目序号
	
	private Date addtime;
	
	private Date updatetime;

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

	public String getIstrue() {
		return istrue;
	}

	public void setIstrue(String istrue) {
		this.istrue = istrue;
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

	
	
	
	
}
