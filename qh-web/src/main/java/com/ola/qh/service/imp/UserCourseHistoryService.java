package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserCourseHistoryDao;
import com.ola.qh.entity.UserCourseHistory;
import com.ola.qh.service.IUserCourseHistoryService;

@Service
public class UserCourseHistoryService implements IUserCourseHistoryService{

	@Autowired
	private UserCourseHistoryDao userCourseHistoryDao;
	
	@Override
	public List<UserCourseHistory> selectUserCourseHistory(String userId,int pageNo,int pageSize) {
		
		return userCourseHistoryDao.selectUserCourseHistory(userId,pageNo,pageSize);
	}

	@Override
	public int insertUserCourseHistory(UserCourseHistory userCourseHistory) {
		
		return userCourseHistoryDao.insertUserCourseHistory(userCourseHistory);
	}
	
	@Override
	public int updateUserCourseHistory(String classId, String userId,Date addtime) {
		
		return userCourseHistoryDao.updateUserCourseHistory(classId, userId,addtime);
	}
	@Override
	public int deleteUserCourseHistory(String id, String userId) {
		
		return userCourseHistoryDao.deleteUserCourseHistory(id, userId);
	}

	@Override
	public UserCourseHistory existUserCourseHistory(String userId, String classId) {
		
		return userCourseHistoryDao.existUserCourseHistory(userId, classId);
	}

	

	

	
}
