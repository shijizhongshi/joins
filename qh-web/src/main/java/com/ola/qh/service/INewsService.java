package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.News;
import com.ola.qh.util.Results;

public interface INewsService {

	public List<News> selectNewList(int pageNo,int pageSize,String types);
	
	public Results<News> singlenews(String id,String userId);
}
