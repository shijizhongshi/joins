package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.News;

public interface INewsService {

	public List<News> selectNewList(int pageNo,int pageSize,String types);
	
	public News singlenews(String id);
}
