package com.ola.qh.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class User {

	private String id;
	
	@NotEmpty(message="用户名不能为空")
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
