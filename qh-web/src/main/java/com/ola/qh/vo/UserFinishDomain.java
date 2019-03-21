package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ola.qh.entity.QuestionBankUserFinish;


public class UserFinishDomain {
	
	@NotNull
	@Size(min=1)
	private List<QuestionBankUserFinish> list=new ArrayList<QuestionBankUserFinish>();
	
	@NotEmpty
	private String userId;
	
	public List<QuestionBankUserFinish> getList() {
		return list;
	}
	public void setList(List<QuestionBankUserFinish> list) {
		this.list = list;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
