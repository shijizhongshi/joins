package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.News;

public interface NewsDao {
	
	public List<News> selectNewList(
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("contentTypes") String contentTypes,
			@Param("typename")String typename);
	
	public News singlenews(@Param("id") String id);
	
	public List<News> selectRecommendNews();


}
