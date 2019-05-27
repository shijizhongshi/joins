package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.util.Results;

public interface IUserVideoService {

	public int saveUV(UserVideo uv);
	
	public int existVideo(String videoId);
	
	public int updateImage(String videoId,String firstImage);
	
	public Results<List<UserVideo>> list(String userId,int pageNo,int pageSize,int types);
	
	public Results<String> update(String userId,String id,int likeNumber,int types);
	
	public Results<String> insertComment(UserVideoComment vc);
	
	public Results<List<UserVideoComment>> listComment(String vid,String userId,int pageNo,int pageSize);
	
	public int savesingleUV(UserVideo uv);
	
}
