package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IUserBuyCourseService;
import com.ola.qh.vo.CourseClassDomain;

@Service
public class UserBuyCourseService implements IUserBuyCourseService {

	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserBuyCourse> selectUserBuyCourse(String userId, String mobile, int types, String years) {
		if (userId == null || "".equals(userId)) {
			User user = userDao.singleUser(null, mobile);
			userId = user.getId();
		}
		List<UserBuyCourse> list = userBuyCourseDao.selectUserBuyCourse(userId, types, years);
		for (UserBuyCourse userBuyCourse : list) {
			if (userBuyCourse.getClassId() != null && !"".equals(userBuyCourse.getClassId())) {

				CourseClassDomain ccd = new CourseClassDomain();
				ccd.setClassId(userBuyCourse.getClassId());
				List<Course> listcourse = courseDao.courseList(ccd);
				int size = 0;
				for (Course course : listcourse) {
					Integer classChapterSize = courseDao.courseChapterCount(course.getId());
					size = size + classChapterSize;
				}
				listcourse.get(0).setCourseChapterSize(size);

				// userBuyCourse.setListcourse(listcourse);
				userBuyCourse.setClassChapterSize(size);

			} else {
				////// 共多少章的课程
				Integer classChapterSize = courseDao.courseChapterCount(userBuyCourse.getCourseId());
				Course c = courseDao.singleCourse(userBuyCourse.getCourseId());
				if (classChapterSize != null) {
					c.setCourseChapterSize(classChapterSize.intValue());
				}
				// userBuyCourse.getListcourse().add(c);
				userBuyCourse.setClassChapterSize(c.getCourseChapterSize());

			}
//		for (UserBuyCourse userBuyCourse : list) {
//			
//			if(userBuyCourse.getClassId()==null || "".equals(userBuyCourse.getClassId())){
//				Course course = courseDao.singleCourse(userBuyCourse.getCourseId());
//				userBuyCourse.setClassId(course.getClassId());
//			}
//		}
		}
		return list;
	}
}
