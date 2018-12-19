package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.service.ICourseClassService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CourseClassDomain;
import com.ola.qh.vo.CourseClassVo;
@Service
public class CourseClassService implements ICourseClassService{

	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<CourseNofree> nofreeList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		List<CourseNofree> list = courseClassDao.nofreeList(ccd);
		
		return list;
	}

	@Override
	public CourseNofree nofreeSingle(String id) {
		// TODO Auto-generated method stub
		return courseClassDao.nofreeSingle(id);
	}

	/**
	 * 
	 */
	@Override
	public List<CourseClass> classList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		return courseClassDao.classList(ccd);
	}

	@Override
	public Results<CourseClassVo> classSingle(String classId) {
		// TODO Auto-generated method stub
		Results<CourseClassVo> result=new Results<CourseClassVo>();
		CourseClassVo vo=new CourseClassVo();
		CourseClass cc = courseClassDao.classSingle(classId);
		BeanUtils.copyProperties(cc, vo);
		Course c=new Course();
		c.setClassId(classId);
		List<Course> clist = courseDao.courseList(c);
		int buycount=0;
		int sectionCount=0;
		buycount = courseClassDao.ordersCount(classId);
		for (Course course : clist) {
		    ////总节数
			sectionCount+=courseDao.sectionCount(course.getId());
			////总的购买人数
			buycount=buycount+courseClassDao.ordersCount(course.getClassId());
		}
		vo.setSectionCount(sectionCount);
		vo.setBuyCount(buycount);
		vo.setCourselist(clist);
		List<CourseTeacher> ctlist = courseClassDao.teacherList(classId);
		vo.setTeacherlist(ctlist);
		////总的老师数
		int teacherCount = courseClassDao.teacherCount(classId);
		vo.setTeacherCount(teacherCount);
		
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

	
}
