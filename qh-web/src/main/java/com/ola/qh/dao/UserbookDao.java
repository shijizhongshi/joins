package com.ola.qh.dao;

import com.ola.qh.entity.Userbook;

public interface UserbookDao {

	public int selectUserbook(String userId);
	
	public int saveUserbook(Userbook userbook);
	
	public int updatetUserbook(Userbook userbook);
}
