package com.ola.qh.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class UserDouDou {

	private String id;
	@NotEmpty
	private String userId;
	@NotNull
	private int status;////1:进豆豆  2出豆豆
	@NotNull
	private int doudou;
	@NotEmpty
	private String describe;
	
	private Date addtime;
	
	private String showtime;////展示的时间
	
	

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDoudou() {
		return doudou;
	}

	public void setDoudou(int doudou) {
		this.doudou = doudou;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
