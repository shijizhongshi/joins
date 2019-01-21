package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserMessage;

public interface UserMessageDao {

	public int insert(UserMessage um);
	
	public List<UserMessage> listByType(@Param("userId")String userId,@Param("types")int types);
	
	public List<UserMessage> list(@Param("userId")String userId,
			@Param("types")int types,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
}
