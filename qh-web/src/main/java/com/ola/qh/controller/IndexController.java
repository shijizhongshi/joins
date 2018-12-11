package com.ola.qh.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.News;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.IndexVo;
import com.ola.qh.vo.ShopDrugDomain;

@RestController
@RequestMapping("/api/index")
public class IndexController {

	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private CourseDao courseDao;

	@RequestMapping("/select")
	public Results<IndexVo> selectList() {
		Results<IndexVo> result = new Results<IndexVo>();
		IndexVo vo = new IndexVo();
		List<Banner> bannerlist = bannerDao.selectBanner("1");
		vo.setBannerlist(bannerlist);
		List<Shop> shoplist = shopDao.listShop(null, null, 0, 6, 1, 0);
		vo.setShoplist(shoplist);
		ShopDrugDomain sdd = new ShopDrugDomain();
		sdd.setIshot(1);
		sdd.setStatus(0);
		sdd.setPageNo(0);
		sdd.setPageSize(6);
		List<ShopDrug> druglist = shopDrugDao.selectDrugList(sdd);
		vo.setDruglist(druglist);
		Course course = new Course();
		course.setCourseExcellent(1);
		course.setPageNo(0);
		course.setPageSize(6);
		List<Course> courselist = courseDao.courseList(course);
		vo.setCourselist(courselist);
		List<News> newlist = newsDao.selectNewList(0, 6,null);
		for (News news : newlist) {
			/// 封装这个对象的时间参数
			if (news.getAddtime() != null) {
				String times = getTime(news.getAddtime(),new Date());
				news.setShowtime(times);
			}

		}
		vo.setNewlist(newlist);
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

	/**
	 * 
	 * <p>Title: getTime</p>  
	 * <p>Description: </p>  
	 * @param date1(实际添加的时间)
	 * @param date2(现在的时间)
	 * @return
	 */
	public static String getTime(Date date1, Date date2) {
		BigDecimal timesum = new BigDecimal(date2.getTime() - date1.getTime());
		BigDecimal bd = new BigDecimal(24 * 60 * 60 * 1000);
		BigDecimal day = new BigDecimal(timesum.doubleValue() / bd.doubleValue()).setScale(2, BigDecimal.ROUND_UP);
		String time1 = "";
		 if (day.compareTo(new BigDecimal(1)) == 1 && day.compareTo(new BigDecimal(2)) == -1) {
			time1 = "昨天";
		} else if (day.compareTo(new BigDecimal(0)) == 1 && day.compareTo(new BigDecimal(1)) == -1) {
			time1 = "今天";
		}else{
			time1 = Patterns.sfTime(date1);
		}
		return time1;
	}

}
