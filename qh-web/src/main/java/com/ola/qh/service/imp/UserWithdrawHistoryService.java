package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserWithdrawHistoryDao;
import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.service.IUserWithdrawHistoryService;

@Service
public class UserWithdrawHistoryService implements IUserWithdrawHistoryService{

	@Autowired
	private UserWithdrawHistoryDao userWithdrawHistoryDao;
	
	@Override
	public List<UserWithdrawHistory> selectUserWithdrawHistory(String userId,int pageNo,int pageSize) {
		
		return userWithdrawHistoryDao.selectUserWithdrawHistory(userId, pageNo, pageSize);
	}

	@Override
	public int saveUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory) {
		
		return userWithdrawHistoryDao.saveUserWithdrawHistory(userwithdrawhistory);
	}

	@Override
	public int deleteUserWithdrawHistory(String id) {
		
		return userWithdrawHistoryDao.deleteUserWithdrawHistory(id);
	}

}
