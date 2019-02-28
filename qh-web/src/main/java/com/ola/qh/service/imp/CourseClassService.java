package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.CourseNofree;
import com.ola.qh.entity.CourseTeacher;
import com.ola.qh.entity.User;
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
	
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BannerDao bannerDao;
	
	@Override
	public List<CourseNofree> nofreeList(CourseClassDomain ccd) {
		// TODO Auto-generated method stub
		List<CourseNofree> list = courseClassDao.nofreeList(ccd);
		
		return list;
	}

	@Override
	public CourseNofree nofreeSingle(String id,String address,String userId) {
		// TODO Auto-generated method stub
		CourseNofree coursenoFree=new CourseNofree();
		String newAddress=null;
		if(userId!=null && !"".equals(userId)){
			/////////查这个用户是否所属加盟商
			String businessId=businessDao.singleBusinessUser(userId);
			if(businessId!=null){
				//////属于某个加盟商
				Business b = businessDao.single(businessId,null);
				if(b!=null){
					coursenoFree.setLogos(b.getLogo());
					coursenoFree.setMobile(b.getMobile());
				}
			}else{
				//////查这个用户是否存在地址
				User user = userDao.singleUser(userId, null);
				if(user.getAddress()!=null && !"".equals(user.getAddress())){
					newAddress=user.getAddress();
				}
			}
		}
		if(newAddress==null && (coursenoFree.getLogos()==null || "".equals(coursenoFree.getLogos()))){
			newAddress=address;
		}
		if(newAddress!=null && !"".equals(newAddress)){
			
			Business b = businessDao.single(null,newAddress);
			if(b!=null){
				////////查加盟商(加盟商存在的话将其的banner赋值)
				coursenoFree.setLogos(b.getLogo());
				coursenoFree.setMobile(b.getMobile());
			}else{
			////没有查到这个地址的加盟商的时候就是系统的banner
				List<Banner> banner=bannerDao.selectBanner("2");
				coursenoFree.setLogos(banner.get(0).getImageurl());
				coursenoFree.setMobile(banner.get(0).getOutLinks());
			}
		}
		
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
	public Results<CourseClassVo> classSingle(String classId,String userId,String address) {
		// TODO Auto-generated method stub
		
		Results<CourseClassVo> result=new Results<CourseClassVo>();
		CourseClassVo vo=new CourseClassVo();
		int count=0;
		String newAddress=null;
		if(userId!=null && !"".equals(userId)){
			count=userBuyCourseDao.selectUserBuyCourseCount(userId, classId, null);
			if(count>0){
				vo.setClassStatus(1);
			}
			/////////查这个用户是否所属加盟商
			String businessId=businessDao.singleBusinessUser(userId);
			if(businessId!=null){
				//////属于某个加盟商
				Business b = businessDao.single(businessId,null);
				if(b!=null){
					vo.setLogos(b.getLogo());
					vo.setMobile(b.getMobile());
				}
			}else{
				//////查这个用户是否存在地址
				User user = userDao.singleUser(userId, null);
				if(user.getAddress()!=null && !"".equals(user.getAddress())){
					newAddress=user.getAddress();
				}
			}
		}
		if(newAddress==null && (vo.getLogos()==null || "".equals(vo.getLogos()))){
			newAddress=address;
		}
		if(newAddress!=null && !"".equals(newAddress)){
			
			Business b = businessDao.single(null,newAddress);
			if(b!=null){
				////////查加盟商(加盟商存在的话将其的banner赋值)
				vo.setLogos(b.getLogo());
				vo.setMobile(b.getMobile());
			}else{
			////没有查到这个地址的加盟商的时候就是系统的banner
				List<Banner> banner=bannerDao.selectBanner("2");
				vo.setLogos(banner.get(0).getImageurl());
				vo.setMobile(banner.get(0).getOutLinks());
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
