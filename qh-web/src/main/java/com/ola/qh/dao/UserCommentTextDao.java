package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserCommentText;

public interface UserCommentTextDao {

	public List<UserCommentText> selectUserCommentText(@Param("commentId")String commentId);
	
	public int insertUserCommentText(UserCommentText userCommentText);
	
	public int deleteUserCommentText(@Param("commentId")String commentId,@Param("userId")String userId);
}
