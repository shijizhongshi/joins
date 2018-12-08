package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IUserBuyCourseService;

@Service
public class UserBuyCourseService implements IUserBuyCourseService{

	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	
	@Override
	public List<UserBuyCourse> selectUserBuyCourse(String userId) {
		// TODO Auto-generated method stub
		return userBuyCourseDao.selectUserBuyCourse(userId);
	}

}
