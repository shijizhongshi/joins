package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserShare;

public interface UserShareDao {

	public UserShare selectUserShare(@Param("userId")String userId,@Param("sectionId")String sectionId);
	
	public int insertUserShare(UserShare userShare);
	
	public int deleteUserShare(@Param("userId")String userId);
}
