package com.ola.qh.dao;

import com.ola.qh.entity.UserBook;

public interface UserBookDao {

	public int selectUserBook(String userId);
	
	public int saveUserBook(UserBook userbook);
	
	public int updatetUserBook(UserBook userbook);
}
