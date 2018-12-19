package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.IUserService;
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
		// TODO Auto-generated method stub
		return courseDao.courseTypeList();///// 大类别的查询
	}

	@Override
	public List<CourseTypeSubclass> courseTypeSubclassList(String courseTypeId) {
		// TODO Auto-generated method stub
		return courseDao.courseTypeSubclassList(courseTypeId);//// 子类别的查询
	}

	@Override
	public List<Course> courseList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		return courseDao.courseList(ccd);
	}

	@Override
	public List<CourseChapter> courseChapterList(String courseId) {
		// TODO Auto-generated method stub
		return courseDao.courseChapterList(courseId);
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
		if (userId != null && !"".equals(userId)) {
			Results<String> userresult = userService.existUser(userId);
			if ("1".equals(userresult.getStatus())) {
				result.setStatus("1");
				result.setMessage(userresult.getMessage());
				return result;
			}
		}
		Course c = courseDao.singleCourse(courseId);
		if(c!=null && c.getCourseShow()==1){
			result.setStatus("1");
			result.setMessage("课程已失效");
			return result;
		}
		int count = userFavoriteDao.existUserFavorite(courseId, userId);
		if (count > 0) {
			c.setIsFavorite(1);
		}
		result.setStatus("0");
		result.setData(c);
		return result;
	}

}
