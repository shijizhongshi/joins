package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.NewsDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.News;
import com.ola.qh.service.INewsService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: NewsService
 * @Description: 不同类别的新闻资讯
 * @author Administrator
 * @date 2018年11月15日
 *
 */
@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsDao newsDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserFavoriteDao userFavoriteDao;
	
	@Override
	public List<News> selectNewList(int pageNo,int pageSize,String contentType,String typename) {
		// TODO Auto-generated method stub
		return newsDao.selectNewList(pageNo,pageSize,contentType,typename);
	}


	@Override
	public Results<News> singlenews(String id,String userId) {
		// TODO Auto-generated method stub
		Results<News> result=new Results<News>();
		if(userId!=null && !"".equals(userId)){
			Results<String> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setMessage(userResult.getMessage());
				result.setStatus("1");
				return result;
			}
		}
		
		News news = newsDao.singlenews(id);
		if(news!=null && news.getStatus()==1){
			result.setStatus("1");
			result.setMessage("文章已失效");
			return result;
		}
		int count = userFavoriteDao.existUserFavorite(id, userId);
		if(count>0){
			news.setIsFavorite(1);////表示已经收藏过了
		}
		if(news.getAddtime()!=null){
			String time = Patterns.sfTime(news.getAddtime());
			news.setShowtime(time);
		}
		
		result.setData(news);
		result.setStatus("0");
		return result;
	}

}
