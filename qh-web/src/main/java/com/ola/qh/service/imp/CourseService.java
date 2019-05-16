package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineCCresult;
import com.ola.qh.entity.CourseLineCheck;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.entity.LiveMark;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.IPushService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;

/**
 * 
 * @ClassName: CourseService
 * @Description: 类别的查询和课程的查询
 * @author guoyuxue
 * @date 2018年11月19日
 *
 */
@Service
public class CourseService implements ICourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private IUserService userService;

	@Autowired
	private UserFavoriteDao userFavoriteDao;

	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private IPushService pushService;

	@Override
	public List<CourseType> courseTypeList() {

		return courseDao.courseTypeList(null);
	}

	@Override
	public List<CourseType> courseTypeSubclassList(String courseTypeId) {
		List<CourseType> list = courseDao.courseTypeList(courseTypeId);
		//////// 查出一个集合来
		for (CourseType courseType : list) {
			if (courseTypeId != null && !"".equals(courseTypeId)) {
				List<CourseTypeSubclass> sublist = courseDao.courseTypeSubclassList(courseTypeId);
				courseType.setSublist(sublist);
				for (CourseTypeSubclass courseTypeSubclass : sublist) {
					// 根据二级ID查是否有三级
					Integer size = courseDao.selectMiniByTypeSubclassId(courseTypeSubclass.getId());
					courseTypeSubclass.setSize(size);
				}
			} else {
				List<CourseTypeSubclass> sublist = courseDao.courseTypeSubclassList(courseType.getId());
				courseType.setSublist(sublist);
				for (CourseTypeSubclass courseTypeSubclass : sublist) {
					// 根据二级ID查是否有三级
					Integer size = courseDao.selectMiniByTypeSubclassId(courseTypeSubclass.getId());
					courseTypeSubclass.setSize(size);
				}
			}

		}
		/// 通过大类别的查询
		return list;/////
	}

	@Override
	public List<Course> courseList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		List<Course> list = courseDao.courseList(ccd);
		for (Course course : list) {
			//// 总的章数
			int num = courseDao.courseChapterCount(course.getId());
			course.setCourseChapterSize(num);
		}
		return list;
	}

	@Override
	public List<CourseChapter> courseChapterList(String courseId) {
		// TODO Auto-generated method stub
		List<CourseChapter> list = courseDao.courseChapterList(courseId);
		for (CourseChapter courseChapter : list) {
			int num = courseDao.courseSectionCount(courseChapter.getId());
			courseChapter.setCourseSectionSize(num);
		}
		return list;
	}

	@Override
	public List<CourseSection> courseSectionList(String courseChapterId) {
		// TODO Auto-generated method stub
		return courseDao.courseSectionList(courseChapterId);
	}

	@Override
	public Results<Course> singleCourse(String courseId, String userId) {
		// TODO Auto-generated method stub
		Results<Course> result = new Results<Course>();
		Course c = courseDao.singleCourse(courseId);
		if (c != null && c.getCourseShow() == 1) {
			result.setStatus("1");
			result.setMessage("课程已失效");
			return result;
		}
		if (userId != null && !"".equals(userId)) {
			Results<User> userResult = userService.existUser(userId);
			if ("1".equals(userResult.getStatus())) {
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId = userResult.getData().getId();
			int count = userFavoriteDao.existUserFavorite(courseId, userId);
			if (count > 0) {
				c.setIsFavorite(1);
			}
		}

		result.setStatus("0");
		result.setData(c);
		return result;
	}

	@Override
	public List<CourseLineShow> selectLiveList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		return courseDao.selectLiveList(ccd);
	}

	@Override
	public CourseLineShow singleLiveShow(String roomId) {
		// TODO Auto-generated method stub
		return courseDao.singleLiveShow(roomId);
	}

	@Override
	public int updateListShow(CourseLineShow cls) {
		// TODO Auto-generated method stub

		return courseDao.updateListShow(cls);
	}

	@Override
	public int insertCCresult(CourseLineCCresult ccresult) {
		// TODO Auto-generated method stub
		return courseDao.insertCCresult(ccresult);
	}

	@Override
	public int insertLineCheck(CourseLineCheck clc) {
		// TODO Auto-generated method stub
		return courseDao.insertLineCheck(clc);
	}

	@Override
	public int updateLineCheck(String courseTypeSubclassName, String roomid, Date updatetime, String id) {
		// TODO Auto-generated method stub
		return courseDao.updateLineCheck(courseTypeSubclassName, roomid, updatetime, id);
	}

	@Override
	public CourseLineCheck singleLineCheck(String mobile) {
		// TODO Auto-generated method stub
		return courseDao.singleLineCheck(mobile);
	}

	@Override
	public Results<List<CourseTypeSubclassNames>> selectThree(String courseTypeSubclassId) {
		Results<List<CourseTypeSubclassNames>> results = new Results<List<CourseTypeSubclassNames>>();
		List<CourseTypeSubclassNames> list = courseDao.select(courseTypeSubclassId);
		results.setStatus("0");
		results.setData(list);

		return results;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Results<String> acquire(String lineShowId, String userId) {
		Results<String> results = new Results<String>();
		// token转userId
		UserLogin userLogin = userLoginDao.selectUserLogin(null, userId);
		String newUserId = null;
		if (!"".equals(userLogin)) {
			newUserId = userLogin.getUserId();
		}
		// 根据直播ID查询直播信息
		CourseLineShow courseLineShow = courseDao.selectById(lineShowId);
		Integer count = 0;
		if (courseLineShow.getStarttime() != null) {
			LiveMark liveMark = new LiveMark();
			liveMark.setId(KeyGen.uuid());
			liveMark.setUserId(newUserId);
			liveMark.setLiveId(courseLineShow.getLiveId());
			liveMark.setLiveName(courseLineShow.getLiveName());
			liveMark.setStarttime(courseLineShow.getStarttime());
			// long s =courseLineShow.getStarttime().getTime();
			liveMark.setStatus(0);
			count = courseDao.insertLiveMark(liveMark);
		}
		if (count != 0) {
			results.setStatus("0");

			return results;
		}
		results.setStatus("1");

		return results;
	}

	/**
	 * 定时推送直播提醒（间隔一小时）
	 */
	@Override
	public void timedPushOneHour() {
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		System.out.println("当前时间" + sf.format(now));
		//获取直播集合
		List<Date> dateList = courseDao.selectLiveShow(sf.format(now));
		for (Date date : dateList) {
			System.out.println(date);
		}
		// 获取需要推送的直播集合
		List<LiveMark> list = courseDao.selectStartTime(sf.format(now));
		long currentTime = System.currentTimeMillis();
		for (LiveMark liveMark : list) {
			//直播开始时间转换
			//long timesTamp = liveMark.getStarttime().getTime();
			
			//测试时间
			long timesTamp = System.currentTimeMillis();
			// 时间差小于一个小时 发送
			if (timesTamp - currentTime <= 60 * 60 * 1000) {
				String userId = liveMark.getUserId();
				// 标题
				String title = "直播即将开始";
				SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
				// 推送
				String content = "您预约的直播课将于" + sFormat.format(liveMark.getStarttime()) + "开始，记得准时观看哦~";
				try {
					pushService.send(userId, title, content);
					// 根据userId更新状态值
					courseDao.updateStatus(userId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
