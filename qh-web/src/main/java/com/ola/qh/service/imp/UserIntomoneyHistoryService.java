package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserIntomoneyHistoryDao;
import com.ola.qh.entity.UserIntomoneyHistory;
import com.ola.qh.service.IUserIntomoneyHistoryService;
@Service
public class UserIntomoneyHistoryService implements IUserIntomoneyHistoryService{

	@Autowired
	private UserIntomoneyHistoryDao userIntomoneyHistoryDao;

	@Override
	public List<UserIntomoneyHistory> selectUserIntomoneyHistory(String userId, int pageNo, int pageSize) {
		
		return userIntomoneyHistoryDao.selectUserIntomoneyHistory(userId, pageNo, pageSize);
	}

	@Override
	public int saveUserIntomoneyHistory(UserIntomoneyHistory userIntomoneyHistory) {
		
		return userIntomoneyHistoryDao.saveUserIntomoneyHistory(userIntomoneyHistory);
	}

	@Override
	public int deleteUserIntomoneyHistory(String id) {
		
		return userIntomoneyHistoryDao.deleteUserIntomoneyHistory(id);
	}

	
	
	

}
