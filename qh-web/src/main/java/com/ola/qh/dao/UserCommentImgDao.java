package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.UserCommentImg;

public interface UserCommentImgDao {

	public List<UserCommentImg> selectUserCommentImg(String commentId);
	
	public int insertUserCommentImg(UserCommentImg usercommentimg);
	
	public int deleteUserCommentImg(String commentId);
	
	public int deleteAllUserCommentImg(String userId);
}
