package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.CourseType;
import com.ola.qh.entity.CourseTypeSubclass;
import com.ola.qh.entity.User;
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
		
		return courseDao.courseTypeList(null);
	}

	@Override
	public List<CourseType> courseTypeSubclassList(String courseTypeId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<CourseType> list=courseDao.courseTypeList(courseTypeId);
					////////查出一个集合来
					for (CourseType courseType : list) {
						if(courseTypeId!=null && !"".equals(courseTypeId)){
							List<CourseTypeSubclass> sublist = courseDao.courseTypeSubclassList(courseTypeId);
							courseType.setSublist(sublist);
						}else{
							List<CourseTypeSubclass> sublist = courseDao.courseTypeSubclassList(courseType.getId());
							courseType.setSublist(sublist);
						}
						
					}
				///通过大类别的查询
				return list;///// 
	}

	@Override
	public List<Course> courseList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		List<Course> list= courseDao.courseList(ccd);
		for (Course course : list) {
			////总的章数
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
		if(c!=null && c.getCourseShow()==1){
			result.setStatus("1");
			result.setMessage("课程已失效");
			return result;
		}
		if (userId != null && !"".equals(userId)) {
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
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

}
