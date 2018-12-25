package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserComment;
import com.ola.qh.util.Results;

public interface IUserCommentService {

	public Results<List<UserComment>> selectShopUserComment(String shopId,String doctorId,int page);
	
	public Results<String> insertUserComment(UserComment usercomment);
	
	public Results<String> insertDoctorComment(UserComment usercomment);
	
	public List<String> selectCommentText(int textStatus);
}
