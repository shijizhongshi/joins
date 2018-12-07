package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.UserFavorite;
import com.ola.qh.service.IUserFavoriteService;

@Service
public class UserFavoriteService implements IUserFavoriteService{

	@Autowired
	private UserFavoriteDao userFavoriteDao;

	@Override
	public List<UserFavorite> selectUserFavorite(String userId,int pageNo,int pageSize,int productType) {
		
		return userFavoriteDao.selectUserFavorite(userId, pageNo, pageSize, productType);
	}

	@Override
	public int insertUserFavorite(UserFavorite userFavorite) {
		
		return userFavoriteDao.insertUserFavorite(userFavorite);
	}

	@Override
	public int deleteUserFavorite(String id) {
		
		return userFavoriteDao.deleteUserFavorite(id);
	}
}
