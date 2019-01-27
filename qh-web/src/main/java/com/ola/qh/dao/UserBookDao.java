package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserDouDou;

public interface UserBookDao {

	public UserBook singleUserBook(String userId);
	
	public int saveUserBook(UserBook userbook);
	
	public int updateUserBook(UserBook userbook);
	
	///////////保存豆豆
	public int insertDoudou(UserDouDou udd);
	
	//////////查询所有的豆豆的来源
	public List<UserDouDou> listDoudou(@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
}
