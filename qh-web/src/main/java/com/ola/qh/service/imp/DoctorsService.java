package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorsService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class DoctorsService implements IDoctorsService{

	@Autowired
	private DoctorsDao doctorsDao;
	@Autowired
	private IUserService userService;
	
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
	public List<Doctors> listDoctors(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return doctorsDao.listDoctor(pageNo,pageSize);
	}

	@Override
	public Doctors singleDoctors(String id,String userId,String islimit) {
		// TODO Auto-generated method stub
		return doctorsDao.singleDoctors(id,userId,islimit);
	}

	@Override
	public Results<String> patientSaveUpdate(DoctorPatient dp) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		Results<String> resultuser = userService.existUser(dp.getUserId());
		if("1".equals(resultuser.getStatus())){
			return resultuser;
		}
		if(dp.getId()!=null && !"".equals(dp.getId())){
			////修改患者的信息
			dp.setUpdatetime(new Date());
			if(dp.getImglist()!=null && dp.getImglist().size()!=0){
				/////新进来图片集合
				List<DoctorPatientImg> newimg = dp.getImglist();
				////原有数据
				List<DoctorPatientImg> listimg= doctorsDao.listPatientImg(dp.getId());
				for (int i = 0; i < listimg.size(); i++) {
					
					int j = 0;
					for (; j < newimg.size(); j++) {
						if(listimg.get(i).getId().equals(newimg.get(j).getId())){
							break;
						}
					}
					if(newimg.size()==j){
						/////
						doctorsDao.deletePatientImg(listimg.get(i).getId());
					}
				}
				
				for (DoctorPatientImg doctorPatientImg : newimg) {
					/////新增加的图片
					if(doctorPatientImg.getId()==null || "".equals(doctorPatientImg.getId())){
						doctorPatientImg.setAddtime(new Date());
						doctorPatientImg.setPatientId(dp.getId());
						doctorPatientImg.setId(KeyGen.uuid());
						doctorsDao.insertPatientImg(doctorPatientImg);
					}
				}
				
			}
			
			
			doctorsDao.updatePatient(dp);
		}else{
			/////保存患者的信息
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
			doctorsDao.insertPatient(dp);
		}
		return result;
	}

	@Override
	public List<DoctorPatient> listPatient(String userId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return doctorsDao.listPatient(userId, pageNo, pageSize);
	}

}
