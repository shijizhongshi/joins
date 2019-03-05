package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;

public interface UserVideoDao {

	public int insert(UserVideo uv);
	
	public List<UserVideo> list(
			@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize
			);
	
	public int update(UserVideo uv);
	
	public UserVideo single(@Param("id")String id);
	
	public int existVideo(@Param("videoId")String videoId);
	
	
	
	public int insertComment(UserVideoComment vc);
	
	public List<UserVideoComment> listComment(
			@Param("vid")String vid,@Param("commentid")String commentid,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("types")int types);
	
	public int updateComment(@Param("likesNumber")String likesNumber,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
	
	public UserVideoComment singleComment(@Param("id")String id);
	
	
	
}
