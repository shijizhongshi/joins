package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserComment;

public interface UserCommentDao {

	public List<UserComment> selectShopUserComment(@Param("shopId")String shopId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public List<UserComment> selectMyUserComment(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertUserComment(UserComment usercomment);
	
	public int deleteUserComment(@Param("id")String id);
	
	public int deleteAllUserComment(@Param("userId")String userId);
}
