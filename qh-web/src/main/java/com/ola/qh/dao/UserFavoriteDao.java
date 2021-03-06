package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserFavorite;

public interface UserFavoriteDao {

	public List<UserFavorite> selectUserFavorite(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("productType")int productType);
	
	public int insertUserFavorite(UserFavorite userFavorite);
	
	public int update(@Param("productId")String productId,
			@Param("status")int status);
	
	public int deleteUserFavorite(
			@Param("id")String id,
			@Param("userId")String userId,
			@Param("productId")String productId);
	

	public int existUserFavorite(
			@Param("productId")String productId,
			@Param("userId")String userId);
	
	public int favoriteCount(String userId);
	
	public List<UserFavorite> selectSearchUserFavorite(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("productName")String productName);
}
