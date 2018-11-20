package com.ola.qh.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.User;
import com.ola.qh.util.Results;

public interface IUserService {
	public Results<User> saveUsers(User user, HttpServletRequest request);

	public User loginUser(String mobile, String password);

	public User existMobileUser(String mobile);

	public int updateUser(@Param("nickname") String nickname, @Param("headimg") String headimg, @Param("id") String id);
}
