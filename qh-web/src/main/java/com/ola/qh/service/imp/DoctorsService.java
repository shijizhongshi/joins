package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.Reply;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLikes;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class DoctorsService implements IDoctorsService{

	@Autowired
	private DoctorsDao doctorsDao;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private DoctorReplyDao  doctorReplyDao;
	
	@Override
	@Transactional(noRollbackFor=Exception.class)
	public Results<String> doctorsSaveUpdate(Doctors d) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		Results<String> resultuser = userService.existUser(d.getUserId());
		if("1".equals(resultuser.getStatus())){
			return resultuser;
		}
		try {
			if(d.getId()!=null && !"".equals(d.getId())){
				//////修改医生的信息
				d.setUpdatetime(new Date());
				doctorsDao.updateDoctors(d);
			}else{
				//////保存医生的信息
				int count = doctorsDao.doctorsCount(d.getUserId());
				if(count!=0){
					/////该用户已经注册过医生信息
					result.setStatus("1");
					result.setMessage("用户已经注册过医生信息");
					return result;
				}
				d.setId(KeyGen.uuid());
				d.setAddtime(new Date());
				doctorsDao.doctorsInsert(d);
			}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
		
	}

	@Override
	public List<Doctors> listDoctors(int pageNo, int pageSize,String address,String professional,String offices,String name) {
		// TODO Auto-generated method stub
		List<Doctors> list=doctorsDao.listDoctor(pageNo, pageSize, address, professional,offices,name);
		/*for (Doctors doctors : list) {
			Double d = doctorsDao.commentGrade(doctors.getId());
			if(d!=null){
				doctors.setGrade(d.doubleValue());
			}
		}*/
		return list;
	}

	@Override
	public Doctors singleDoctors(String id,String userId,String islimit,int page) {
		// TODO Auto-generated method stub
		Doctors d=  doctorsDao.singleDoctors(id,userId,islimit);
		/*int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		//List<DoctorPatient> list =doctorReplyDao.replyList(patientId, pageNo, pageSize);
		for (DoctorPatient dp : list) {
			/////赛一个匿名用户
			String name="匿名用户";
			Random rand = new Random();
			int num= rand.nextInt(10000)+0;
			dp.setPublisher(name+num);
		}*/
		//d.setList(list);
		return d;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public Results<String> patientSaveUpdate(DoctorPatient dp) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		result = userService.existUser(dp.getUserId());
		if("1".equals(result.getStatus())){
			return result;
		}
		if(dp.getId()!=null && !"".equals(dp.getId())){
			////修改患者的信息
			dp.setUpdatetime(new Date());
			DoctorPatient dpnew = doctorsDao.singlePatient(dp.getId());
			UserLikes ul = doctorReplyDao.singleLikes(dp.getUserId(), dp.getId());
			
			if(ul==null){
				
				
				doctorsDao.updatePatient(dpnew.getLikes()+1, new Date(), dp.getId());
				doctorReplyDao.insertLikes(KeyGen.uuid(), dp.getUserId(), dp.getId(), new Date());
			}else{
				
				doctorsDao.updatePatient(dpnew.getLikes()-1, new Date(), dp.getId());
				doctorReplyDao.deleteLikes(ul.getId());
			}
			
			
		}else{
			/////保存问题的信息
			String patientId=KeyGen.uuid();
			dp.setId(patientId);
			dp.setAddtime(new Date());
			if(dp.getImglist()!=null && dp.getImglist().size()!=0){
				for (DoctorPatientImg imglist : dp.getImglist()){
					imglist.setAddtime(new Date());
					imglist.setPatientId(patientId);
					imglist.setId(KeyGen.uuid());
					doctorsDao.insertPatientImg(imglist);
				}
			}
			User user=userService.sinleUser(dp.getUserId(), null);
			if(user!=null){
				dp.setPublisher(user.getNickname());
				dp.setPublisherHeadImgUrl(user.getHeadimg());
				if(user.getIsdoctor()==1){
					Doctors d = doctorsDao.singleDoctors(null, dp.getUserId(), "1");
					if(d!=null){
						dp.setPublisher(d.getName());
						dp.setPublisherHeadImgUrl(d.getHeadImg());
						dp.setPublisherPosition(d.getProfessional());
					}
				}
					
				
			}
			
			doctorsDao.insertPatient(dp);
		}
		result.setStatus("0");
		return result;
	}
		

	@Override
	public List<DoctorPatient> listPatient(String userId,String category, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<DoctorPatient> list = doctorsDao.listPatient(userId,category, pageNo, pageSize);
		for (DoctorPatient doctorPatient : list) {
			UserLikes ul = doctorReplyDao.singleLikes(userId, doctorPatient.getId());
			if(ul==null){
				/////没有点赞
				doctorPatient.setIslikes(0);
			}else{
				doctorPatient.setIslikes(1);
			}
			doctorPatient.setShowtime(Patterns.sfDetailTime(doctorPatient.getAddtime()));
			//////回复的个数
			int replyCount = doctorReplyDao.replyListCount(doctorPatient.getId());
			doctorPatient.setReplyCount(replyCount);
		}
		return list;
	}

	@Override
	public DoctorPatient singlePatient(String id) {
		// TODO Auto-generated method stub
		DoctorPatient dp = doctorsDao.singlePatient(id);
		
		List<DoctorPatientImg> imglist = doctorsDao.listPatientImg(id);
		dp.setImglist(imglist);
		return dp;
	}

}
