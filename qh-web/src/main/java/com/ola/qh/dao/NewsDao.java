package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.News;
import com.ola.qh.entity.TopicSquare;

public interface NewsDao {
	
	public List<News> selectNewList(
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("contentTypes") String contentTypes,
			@Param("typename")String typename);
	
	public News singlenews(@Param("id") String id);
	
	public List<News> selectRecommendNews();

	//话题广场
	public List<TopicSquare> topicSquare(@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize);
}
