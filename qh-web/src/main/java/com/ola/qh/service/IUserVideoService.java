package com.ola.qh.service;

import java.security.DigestException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.util.Results;

public interface IUserVideoService {

	public Results<String> save(UserVideo uv) throws DigestException; 
	
	public int saveUV(UserVideo uv);
	
	public int existVideo(String videoId);
	
	public Results<List<UserVideo>> list(String userId,int pageNo,int pageSize,int types);
	
	public Results<String> update(String userId,String id,int likeNumber,int types);
	
	public Results<String> insertComment(UserVideoComment vc);
	
	public Results<List<UserVideoComment>> listComment(String vid,String userId,int pageNo,int pageSize);
	
}
