package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.UserFavorite;

public interface UserFavoriteDao {

	public List<UserFavorite> selectUserFavorite(@Param("userId")String userId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("productType")int productType);
	
	public int insertUserFavorite(UserFavorite userFavorite);
	
	public int deleteUserFavorite(@Param("id")String id);
	
	public int existUserFavorite(@Param("productId")String productId);
}
