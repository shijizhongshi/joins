package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.News;
import com.ola.qh.entity.TopicSquare;
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
	@Autowired
	private DoctorsDao doctorsDao;

	@Override
	public List<News> selectNewList(int pageNo, int pageSize, String contentType, String typename) {
		// TODO Auto-generated method stub
		return newsDao.selectNewList(pageNo, pageSize, contentType, typename);
	}

	@Override
	public Results<News> singlenews(String id, String userId) {
		// TODO Auto-generated method stub
		Results<News> result = new Results<News>();
		if (userId != null && !"".equals(userId)) {
			Results<String> userResult = userService.existUser(userId);
			if ("1".equals(userResult.getStatus())) {
				result.setMessage(userResult.getMessage());
				result.setStatus("1");
				return result;
			}
		}

		News news = newsDao.singlenews(id);
		if (news != null && news.getStatus() == 1) {
			result.setStatus("1");
			result.setMessage("文章已失效");
			return result;
		}
		int count = userFavoriteDao.existUserFavorite(id, userId);
		if (count > 0) {
			news.setIsFavorite(1);//// 表示已经收藏过了
		}
		if (news.getAddtime() != null) {
			String time = Patterns.sfTime(news.getAddtime());
			news.setShowtime(time);
		}

		result.setData(news);
		result.setStatus("0");
		return result;
	}

	@Transactional
	@Override
	public Results<List<TopicSquare>> topicSquare(int pageNo, int pageSize) {

		Results<List<TopicSquare>> results = new Results<List<TopicSquare>>();
		try {
			List<TopicSquare> TopicSquarelist = newsDao.topicSquare(pageNo, pageSize);

			for (TopicSquare topicSquare : TopicSquarelist) {

				List<DoctorPatient> list = doctorsDao.DoctorPatientsList(topicSquare.getTitle());

				topicSquare.setList(list);
			}
			results.setData(TopicSquarelist);
			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
}
