package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.Reply;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLikes;
import com.ola.qh.service.IDoctorReplyService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class DoctorReplyService implements IDoctorReplyService{

	@Autowired
	private DoctorReplyDao doctorReplyDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private DoctorsDao doctorDao;
	
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
		List<DoctorInfo> list=doctorReplyDao.doctorInfoList();
		
		return list;
	}

	/*@Override
	public Results<DoctorReplyVo> singleReply(String patientId,String userId) {
		// TODO Auto-generated method stub
		Results<DoctorReplyVo> result=new Results<DoctorReplyVo>();
		User user=new User();
		if(userId!=null){
			user=userService.sinleUser(userId, null);
		}
		DoctorReplyVo vo=new DoctorReplyVo();
		DoctorPatient dp=doctorDao.singlePatient(patientId);
		
		BeanUtils.copyProperties(dp, vo);
		vo.setRoles(1);
		if(userId!=null){
		if(dp.getUserId().equals(userId) || user.getIsdoctor()==1){
			String readStatus="0";
			String doctorId=null;
			if(user.getIsdoctor()==1){
				
				vo.setRoles(3);
				///////如果是医生的话(说明医生已经读了)
				Doctors d = doctorDao.singleDoctors(null, userId, "1");
				if(d!=null){
					vo.setDoctorId(doctorId);
					doctorId=d.getId();
					List<Reply> list = doctorReplyDao.listByIds(patientId, d.getId());
					if(list!=null && list.size()!=0){
						if(list.get(0).getReadStatus()==0){
							readStatus="2";///医生已读
						}else if(list.get(0).getReadStatus()==1){
							readStatus="3";///都读了
						}else{
							////2没有新消息或者3是都读了
							readStatus=String.valueOf(list.get(0).getReadStatus());
						}
					}
					
				}
			}else{
				vo.setRoles(2);
				//////说明患者已经读了
				List<Reply> list = doctorReplyDao.listByIds(patientId, null);
				if(list!=null && list.size()!=0){
					if(list.get(0).getReadStatus()==0){
						readStatus="1";///患者已读
					}else if(list.get(0).getReadStatus()==2){
						readStatus="3";///都读了
					}else{
						////1没有新消息或者3是都读了
						readStatus=String.valueOf(list.get(0).getReadStatus());
					}
				}
				
			}
			//List<DoctorReply> list = doctorReplyDao.listByIds(patientId, null);
			doctorReplyDao.updateReadStatus(readStatus, patientId, doctorId,0);
			////把图片拿出来(只有是医生的身份或者是用户自己看的时候才能看到图片)
			vo.setImglist(doctorDao.listPatientImg(patientId));
		}
	}
		List<Reply> idslist = doctorReplyDao.listByIds(patientId, null);
		if(idslist!=null && idslist.size()!=0){
			for (Reply doctorReply : idslist) {
				doctorReplyDao.updateReadStatus(null, patientId, null,doctorReply.getBrowseCount()+1);
			}
		}
		List<DoctorsVo> list = doctorReplyDao.doctorReplyList(patientId);
		for (DoctorsVo doctorsVo : list) {
			List<Reply> replyList = doctorReplyDao.listByIds(patientId, doctorsVo.getDoctorId());
			for (Reply doctorReply : replyList) {
				if(doctorReply.getAddtime()!=null){
					doctorReply.setShowtime(Patterns.sfDetailTime(doctorReply.getAddtime()));
				}
			}
			doctorsVo.setList(replyList);
		}
		vo.setList(list);
		if(dp.getAddtime()!=null){
			vo.setPatientTime(Patterns.sfDetailTime(dp.getAddtime()));
		}
		result.setData(vo);
		result.setStatus("0");
		return result;
	}*/

	@Override
	public Results<String> insertReply(Reply dr) {
		// TODO Auto-generated method stub
 		Results<String> result=new Results<String>();
		/*DoctorPatient dp=doctorDao.singlePatient(dr.getPatientId());
		if(dp==null){
			result.setStatus("1");
			result.setMessage("患者信息标识不符");
			return result;
			
		}else if(dp.getIssolve()==1){
			result.setStatus("1");
			result.setMessage("该患者信息已解决");
			return result;
		}
		*/
		
		Results<User> userResult = userService.existUser(dr.getUserId());
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		dr.setUserId(userResult.getData().getId());
		if(dr.getType()==2){
			Reply r = doctorReplyDao.replySingle(dr.getId());
			if(r.getUserId().equals(dr.getUserId())){
				result.setStatus("1");
				result.setMessage("不能回复自己的评论");
				return result;
			}
		}
		User user=userService.sinleUser(dr.getUserId(), null);
		if(user!=null){
			dr.setReplyName(user.getNickname());
			dr.setReplyHeadImg(user.getHeadimg());
			if(user.getIsdoctor()==1){
				Doctors d = doctorDao.singleDoctors(null, dr.getUserId(), "1");
				if(d!=null){
					dr.setReplyName(d.getName());
					dr.setReplyHeadImg(d.getHeadImg());
				}
			}
		}
		dr.setId(KeyGen.uuid());
		dr.setAddtime(new Date());
		doctorReplyDao.insertReply(dr);
		result.setStatus("0");
		return result;
	}

	@Override
	public Results<List<Reply>> listReply(String patientId, int pageNo, int pageSize,String userId) {
		// TODO Auto-generated method stub
		Results<List<Reply>> result=new Results<List<Reply>>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			
			
			userId=userResult.getData().getId();
		}
		List<Reply> list = doctorReplyDao.replyList(patientId, pageNo, pageSize);
		for (Reply reply : list) {
			UserLikes ul = doctorReplyDao.singleLikes(userId, reply.getId());
			if(ul!=null){
				reply.setIslikes(1);
			}else{
				reply.setIslikes(0);
			}
			reply.setShowtime(Patterns.sfDetailTime(reply.getAddtime()));
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	@Override
	@Transactional
	public Results<String> updateReply(String id,String userId) {
		Results<String> result=new Results<String>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
		}
		Reply reply = doctorReplyDao.replySingle(id);
		UserLikes ul = doctorReplyDao.singleLikes(userId, id);
		if(ul==null){
			doctorReplyDao.updateReply(reply.getLikes()+1, new Date(), id);
			doctorReplyDao.insertLikes(KeyGen.uuid(), userId, id, new Date());
		}else{
			 doctorReplyDao.updateReply(reply.getLikes()-1, new Date(), id);
			 doctorReplyDao.deleteLikes(ul.getId());
			 
		}
		result.setStatus("0");
		return result;
		
	}

}
