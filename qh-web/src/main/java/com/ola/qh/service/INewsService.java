package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.News;
import com.ola.qh.entity.TopicSquare;
import com.ola.qh.util.Results;

public interface INewsService {

	public List<News> selectNewList(int pageNo,int pageSize,String contentTypes,String typename);
	
	public Results<News> singlenews(String id,String userId);
	
	public Results<List<TopicSquare>> topicSquare( int pageNo, int pageSize);
}
