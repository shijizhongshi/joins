package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserFavorite;

public interface IUserFavoriteService {

	public List<UserFavorite> selectUserFavorite(String userId);
	
	public int insertUserFavorite(UserFavorite userFavorite);
	
	public int deleteUserFavorite(String id);
}
