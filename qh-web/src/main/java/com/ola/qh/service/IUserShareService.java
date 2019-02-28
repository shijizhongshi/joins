package com.ola.qh.service;

import com.ola.qh.entity.UserShare;

public interface IUserShareService {

	public UserShare selectUserShare(String userId,String sectionId);
	
	public int insertUserShare(UserShare userShare);
	
	public int deleteUserShare(String userId,String sectionId);
}
