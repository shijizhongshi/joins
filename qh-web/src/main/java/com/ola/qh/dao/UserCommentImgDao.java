package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserCommentImg;

public interface UserCommentImgDao {

	public List<UserCommentImg> selectUserCommentImg(String commentId);
	
	public int insertUserCommentImg(UserCommentImg usercommentimg);
	
	public int deleteUserCommentImg(@Param("commentId")String commentId,@Param("userId")String userId);
	
}
