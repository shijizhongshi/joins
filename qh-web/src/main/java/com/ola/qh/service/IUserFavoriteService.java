package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserFavorite;
import com.ola.qh.util.Results;

public interface IUserFavoriteService {

	public Results<List<UserFavorite>> selectUserFavorite(String userId,int pageNo,int pageSize,int productType);
	
	public Results<String> insertUserFavorite(UserFavorite userFavorite);
	
	public Results<String> deleteUserFavorite(String id,String userId,String productId);
	
	public int existUserFavorite(String productId);
	
	public Results<List<UserFavorite>> selectSearchUserFavorite(String userId,int pageNo,int pageSize,String productName);
}
