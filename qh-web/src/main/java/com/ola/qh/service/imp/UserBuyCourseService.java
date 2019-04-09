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

@Service
public class UserBuyCourseService implements IUserBuyCourseService{

	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserDao userDao;
	
	
	
	@Override
	public List<UserBuyCourse> selectUserBuyCourse(String userId,String mobile,int types,String years){
		// TODO Auto-generated method stub
		if(userId==null || "".equals(userId)){
			User user =  userDao.singleUser(null, mobile);
			userId=user.getId();
		}
		
		List<UserBuyCourse> list = userBuyCourseDao.selectUserBuyCourse(userId,types,years);
		/*for (UserBuyCourse userBuyCourse : list) {
			
			if(userBuyCourse.getClassId()==null || "".equals(userBuyCourse.getClassId())){
				Course course = courseDao.singleCourse(userBuyCourse.getCourseId());
				userBuyCourse.setClassId(course.getClassId());
			}
		}*/
		return list;
	}

}
