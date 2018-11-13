package com.ola.qh.dao;

import org.springframework.stereotype.Repository;

import com.ola.qh.entity.User;

public interface UserDao {

	public int saveUser(User user);
	
}
