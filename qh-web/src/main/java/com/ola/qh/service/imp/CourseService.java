package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineCCresult;
import com.ola.qh.entity.CourseLineCheck;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.CourseTypeSubclassNames;
import com.ola.qh.entity.LiveMark;
import com.ola.qh.entity.User;
import com.ola.qh.service.ICourseService;
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
		// 这个接口返回值不要使用result封装好再返回，封装不适用全部的controller
		List<CourseLineShow> list = courseDao.selectLiveList(ccd);
		for (CourseLineShow courseLineShow : list) {
			// 格式化时间
			// 直播日期格式 05-17
			SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
			if (courseLineShow.getStarttime() != null) {
				courseLineShow.setDate(sf.format(courseLineShow.getStarttime()));
			}
			// 直播时间段格式 12:00-13:00
			SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
			if (courseLineShow.getStarttime() != null && courseLineShow.getStoptime() != null) {
				courseLineShow.setStartToEnd(sFormat.format(courseLineShow.getStarttime()) + "-"
						+ sFormat.format(courseLineShow.getStoptime()));
			}
			if (ccd.getUserId() != null && !"".equals(ccd.getUserId())) {
				// 根据userid和liveID查询直播预约表
				Integer countInteger = courseDao.selectCount(ccd.getUserId(), courseLineShow.getId());
				if (countInteger >= 1) {
					courseLineShow.setIsMark(1);
				} else {
					courseLineShow.setIsMark(0);
				}
			} else {
				courseLineShow.setIsMark(0);
			}
		}

		return list;
	}

	@Override
	public CourseLineShow singleLiveShow(String roomId,String liveId) {
		// TODO Auto-generated method stub
		return courseDao.singleLiveShow(roomId,liveId);
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



	public Results<List<CourseTypeSubclassNames>> selectThree(String courseTypeSubclassId) {
		Results<List<CourseTypeSubclassNames>> results = new Results<List<CourseTypeSubclassNames>>();
		List<CourseTypeSubclassNames> list = courseDao.select(courseTypeSubclassId);
		results.setStatus("0");
		results.setData(list);

		return results;
	}

	@Override
	public List<CourseLineWhite> selectAllByLiveId(String liveId) {
		// TODO Auto-generated method stub
		return courseDao.selectAllByLiveId(liveId);
	}

	@Override
	public Results<Integer> acquire(String lineShowId, String userId) {
		Results<Integer> results = new Results<Integer>();
		// token转userId
		Results<User> userresult = userService.existUser(userId);
		if ("0".equals(userresult.getStatus())) {
			userId = userresult.getData().getId();
		} else {
			results.setStatus("1");
			results.setMessage(userresult.getMessage());

			return results;
		}
		// 根据直播的ID查询直播信息
		CourseLineShow courseLineShow = courseDao.selectById(lineShowId);
		Integer count = 0;
		if (courseLineShow != null && courseLineShow.getStarttime() != null) {
			// 根据userid和直播的ID查询 直播预约表
			Integer countInteger = courseDao.selectCount(userId, courseLineShow.getId());
			if (countInteger != 0) {
				results.setStatus("1");
				results.setData(1);
				results.setMessage("重复预约");

				return results;
			}
			LiveMark liveMark = new LiveMark();

			liveMark.setId(KeyGen.uuid());
			liveMark.setUserId(userId);
			liveMark.setLiveId(courseLineShow.getId());
			liveMark.setLiveName(courseLineShow.getLiveName());
			liveMark.setStarttime(courseLineShow.getStarttime());
			// long s =courseLineShow.getStarttime().getTime();
			liveMark.setStatus(0);
			liveMark.setIsMark(1);
			count = courseDao.insertLiveMark(liveMark);
		}
		if (count != 0) {
			results.setMessage("保存预约信息，成功");
			results.setStatus("0");
			results.setData(0);

			return results;
		}
		results.setStatus("1");
		results.setData(0);
		results.setMessage("保存预约信息失败");

		return results;

	}

	@Override
	public List<CourseChapter> courseChapterListAll(String courseId) {
		List<CourseChapter> list = courseDao.courseChapterList(courseId);
		for (CourseChapter courseChapter : list) {
			//获取章下面的节数量
			int num = courseDao.courseSectionCount(courseChapter.getId());
			courseChapter.setCourseSectionSize(num);
			//获取章下面的节集合
			List<CourseSection> sectionList = courseSectionList(courseChapter.getId());
			courseChapter.setCourseSections(sectionList);
		}
		return list;
	}

}
