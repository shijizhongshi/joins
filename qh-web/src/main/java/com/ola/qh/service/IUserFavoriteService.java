package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserFavorite;

public interface IUserFavoriteService {

	public List<UserFavorite> selectUserFavorite(String userId,int pageNo,int pageSize,int productType);
	
	public int insertUserFavorite(UserFavorite userFavorite);
	
	public int deleteUserFavorite(String id);
	
	public int existUserFavorite(String productId);
}
