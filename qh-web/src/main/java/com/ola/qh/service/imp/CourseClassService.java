package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserBuyCourseDao;
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
	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	
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
	public Results<CourseClassVo> classSingle(String classId,String userId) {
		// TODO Auto-generated method stub
		
		Results<CourseClassVo> result=new Results<CourseClassVo>();
		CourseClassVo vo=new CourseClassVo();
		int count=0;
		if(userId!=null && !"".equals(userId)){
			count=userBuyCourseDao.selectUserBuyCourseCount(userId, classId, null);
			if(count>0){
				vo.setClassStatus(1);
			}
			
		}
		
		CourseClass cc = courseClassDao.classSingle(classId);
		BeanUtils.copyProperties(cc, vo);
		CourseClassDomain ccd =new CourseClassDomain();
		ccd.setClassId(classId);
		
		List<Course> clist = courseDao.courseList(ccd);
		int buycount=0;
		/*int sectionCount=0;*/
		buycount = courseClassDao.ordersCount(classId);
		List<CourseTeacher> ctlist = courseClassDao.teacherList(classId);
		/*for (CourseTeacher courseTeacher : ctlist) {
			int courseNumber=0;
		for (Course course : clist) {
		    ////这个教师对应的总节数
			Integer num = courseDao.sectionCount(course.getId(),courseTeacher.getName());
			if(num!=null){
				courseNumber+=num.intValue();
			}
		}
		courseTeacher.setCourseNumber(courseNumber);
		}*/
		for (Course course : clist) {
		    /*////总节数
			if(courseDao.sectionCount(course.getId(),null)!=null){
				sectionCount+=courseDao.sectionCount(course.getId(),null).intValue();
			}*/
			
			int num1 = courseDao.courseChapterCount(course.getId());
			course.setCourseChapterSize(num1);
			
			if(count>0){
				course.setCourseStatus(1);
			}else{
				if(userId!=null && !"".equals(userId)){
					int num = userBuyCourseDao.selectUserBuyCourseCount(userId, null, course.getId());
					if(num>0){
						course.setCourseStatus(1);
					}
				}
				
			}
			
			////总的购买人数
			buycount=buycount+courseClassDao.ordersCount(course.getClassId());
		}
		/*vo.setSectionCount(sectionCount);*/
		vo.setBuyCount(buycount);
		vo.setCourselist(clist);
		
		vo.setTeacherlist(ctlist);
		////总的老师数
		int teacherCount = courseClassDao.teacherCount(classId);
		vo.setTeacherCount(teacherCount);
		
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

	
}
