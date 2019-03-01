package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserShareDao;
import com.ola.qh.entity.UserShare;
import com.ola.qh.service.IUserShareService;

@Service
public class UserShareService implements IUserShareService{

	@Autowired
	private UserShareDao userShareDao;
	
	@Override
	public int insertUserShare(UserShare userShare) {
		
		return userShareDao.insertUserShare(userShare);
	}

	@Override
	public UserShare selectUserShare(String userId, String sectionId) {
		
		return userShareDao.selectUserShare(userId, sectionId);
	}

	@Override
	public int deleteUserShare(String userId, String sectionId) {
		
		return userShareDao.deleteUserShare(userId, sectionId);
	}

	
}
