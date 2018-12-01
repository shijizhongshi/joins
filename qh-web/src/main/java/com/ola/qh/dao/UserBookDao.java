package com.ola.qh.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserBook;

public interface UserBookDao {

	public UserBook selectUserBook(String userId);
	
	public int saveUserBook(UserBook userbook);
	
	public int updateUserBook(
			@Param("userId")String userId,
			@Param("accountMoney")BigDecimal accountMoney,
			@Param("updatetime")Date updatetime);
}
