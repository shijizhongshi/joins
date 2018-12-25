package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserComment;

public interface UserCommentDao {

	public List<UserComment> selectShopUserComment(@Param("shopId")String shopId,@Param("doctorId")String doctorId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int insertUserComment(UserComment usercomment);
	
	public int insertDoctorComment(UserComment usercomment);
	
	public List<String> selectCommentText(@Param("textStatus")int textStatus);
	
}
