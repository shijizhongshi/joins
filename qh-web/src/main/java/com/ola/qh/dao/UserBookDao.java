package com.ola.qh.dao;

import com.ola.qh.entity.UserBook;

public interface UserBookDao {

	public UserBook singleUserBook(String userId);
	
	public int saveUserBook(UserBook userbook);
	
	public int updateUserBook(UserBook userbook);
}
