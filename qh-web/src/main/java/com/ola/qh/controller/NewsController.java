package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.News;
import com.ola.qh.entity.TopicSquare;
import com.ola.qh.service.INewsService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: NewsController
 * @Description: 不同类别的新闻展示
 * @author guoyuxue
 * @date 2018年11月15日
 *
 */
@RestController
@RequestMapping(value="/api/news")
public class NewsController {

	@Autowired
	private INewsService newsService;
	/**
	 * 如果page=1的话说明首页展示的资讯
	 * 如果page是变量的话就是资讯的列表页
	 * @param page
	 * @author guoyuxue
	 * @return
	 */
	@RequestMapping(value="/newLists")
	public Results<List<News>> newsList(
			@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "contentType", required = false) String contentType,
			@RequestParam(name = "typename", required = false) String typename){
		
		Results<List<News>> result = new Results<List<News>>();
		int pageNo = (page - 1) * Patterns.zupageSize;
		
		List<News> lists = newsService.selectNewList(pageNo,Patterns.zupageSize,contentType,typename);
		for (News news : lists) {
			if(news.getAddtime()!=null){
				news.setShowtime(Patterns.sfTime(news.getAddtime()));
			}
			
		}
		
		result.setData(lists);
		result.setStatus("0");
		return result;
		
	}
	/**
	 * <p>Title: newSingle</p>  
	 * <p>Description: 资讯的详情页</p>  
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/single",method=RequestMethod.GET)
	public Results<News> newSingle(
			@RequestParam(name="id",required=true)String id,
			@RequestParam(name="userId",required=false)String userId){
		
		return newsService.singlenews(id,userId);
		
	}
	
	
	@RequestMapping(value="/topic",method=RequestMethod.GET)
	public Results<List<TopicSquare>> newSingle(@RequestParam(name = "page", required = true) int page){
		
		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		
		return newsService.topicSquare(pageNo, pageSize);
		
	}
}
