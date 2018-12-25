package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorReply;
import com.ola.qh.entity.User;
import com.ola.qh.service.IDoctorReplyService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorReplyVo;
import com.ola.qh.vo.DoctorsVo;

@Service
public class DoctorReplyService implements IDoctorReplyService{

	@Autowired
	private DoctorReplyDao doctorReplyDao;
	@Autowired
	private DoctorsDao doctorDao;
	@Autowired
	private IUserService userService;
	
	@Override
	public List<DoctorInfo> officeList() {
		// TODO Auto-generated method stub
		return doctorReplyDao.officeList();
	}

	/**
	 * 
	 */
	@Override
	public List<DoctorInfo> doctorInfoList() {
		// TODO Auto-generated method stub
		return doctorReplyDao.doctorInfoList();
	}

	@Override
	public Results<DoctorReplyVo> singleReply(String patientId,String userId) {
		// TODO Auto-generated method stub
		Results<DoctorReplyVo> result=new Results<DoctorReplyVo>();
		User user = userService.sinleUser(userId, null);
		if(user==null){
			result.setStatus("1");
			result.setMessage("用户标识不符");
			return result;
		}
		DoctorReplyVo vo=new DoctorReplyVo();
		DoctorPatient dp=doctorDao.singlePatient(patientId);
		BeanUtils.copyProperties(dp, vo);
		vo.setRoles(1);
		if(dp.getUserId().equals(userId) || user.getIsdoctor()==1){
			if(user.getIsdoctor()==1){
				vo.setRoles(3);
			}else{
				vo.setRoles(2);
			}
			////把图片拿出来(只有是医生的身份或者是用户自己看的时候才能看到图片)
			vo.setImglist(doctorDao.listPatientImg(patientId));
		}
		List<DoctorsVo> list = doctorReplyDao.doctorReplyList(patientId);
		for (DoctorsVo doctorsVo : list) {
			List<DoctorReply> replyList = doctorReplyDao.listByIds(patientId, doctorsVo.getDoctorId());
			for (DoctorReply doctorReply : replyList) {
				if(doctorReply.getAddtime()!=null){
					doctorReply.setShowtime(Patterns.sfDetailTime(doctorReply.getAddtime()));
				}
			}
			doctorsVo.setList(replyList);
		}
		if(dp.getAddtime()!=null){
			vo.setPatientTime(Patterns.sfDetailTime(dp.getAddtime()));
		}
		result.setData(vo);
		result.setStatus("0");
		return result;
	}

	@Override
	public Results<String> insertReply(DoctorReply dr) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		DoctorPatient dp=doctorDao.singlePatient(dr.getPatientId());
		if(dp==null){
			result.setStatus("1");
			result.setMessage("患者信息标识不符");
			return result;
			
		}else if(dp.getIssolve()==1){
			result.setStatus("1");
			result.setMessage("该患者信息已解决");
			return result;
		}
		
		int num = doctorReplyDao.existDoctor(dr.getDoctorId());
		if(num!=1){
			result.setStatus("1");
			result.setMessage("医生信息标识不符");
			return result;
		}
		dr.setId(KeyGen.uuid());
		dr.setAddtime(new Date());
		doctorReplyDao.insertReply(dr);
		result.setStatus("0");
		return result;
	}

}
