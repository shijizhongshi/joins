package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserBuyCourse;

public interface IUserBuyCourseService {

	public List<UserBuyCourse> selectUserBuyCourse(String userId,String mobile,int types,String years);
}
