package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.UserWithdraw;

public class UserWithdrawVo {

	private String yearMonth;
	
	private List<UserWithdraw> list=new ArrayList<UserWithdraw>();

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public List<UserWithdraw> getList() {
		return list;
	}

	public void setList(List<UserWithdraw> list) {
		this.list = list;
	}
	
	
}
