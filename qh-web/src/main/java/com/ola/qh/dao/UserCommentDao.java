package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserComment;

public interface UserCommentDao {

	public List<UserComment> selectShopUserComment(@Param("shopId")String shopId,@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertUserComment(UserComment usercomment);
	
	public int deleteUserComment(@Param("id")String id,@Param("userId")String userId);
	
	public double selectAvgGrade(@Param("shopId")String shopId);
}
