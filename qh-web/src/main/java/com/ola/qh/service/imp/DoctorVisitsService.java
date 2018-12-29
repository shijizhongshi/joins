package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorVisitsService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorAndPatient;
import com.ola.qh.vo.DoctorVisitsVo;

@Service
public class DoctorVisitsService implements IDoctorVisitsService{

	@Autowired
	private DoctorsDao doctorsDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Transactional
	@Override
	public Results<DoctorVisitsVo> selectDoctorVisits(String offices,int issolve,String userId) {
		
		Results<DoctorVisitsVo> results=new Results<DoctorVisitsVo>();
		//try {
			if(issolve==1){
				
			DoctorVisitsVo visits=new DoctorVisitsVo();
			
			List<DoctorAndPatient> list=doctorsDao.selectFromOffices(offices,null);
			
			for (DoctorAndPatient doctorAndPatient : list) {
				
				List<Doctors> doctorList=doctorsDao.selectDoctorId(doctorAndPatient.getPatientId());
					
				doctorAndPatient.setList(doctorList);
				}
				
			visits.setDoctor(doctorsDao.listRecommendDoctor());
			visits.setNews(newsDao.selectRecommendNews());
			visits.setPatient(list);
			
			List<DoctorAndPatient> exist=doctorsDao.existReadStatus(userId);
			for (DoctorAndPatient promptMessage : exist) {
				
				String isread=promptMessage.getReadStatus();
				
				if(isread.equals(0)){
					
					visits.setReadStatus(isread);
					break;
				}
				visits.setReadStatus(isread);
			}
			
			
			results.setData(visits);
			results.setStatus("0");
			return results;
			}
			else {
				DoctorVisitsVo visits=new DoctorVisitsVo();
				
				List<DoctorAndPatient> list=doctorsDao.selectPatient(issolve);
				
				visits.setDoctor(doctorsDao.listRecommendDoctor());
				visits.setNews(newsDao.selectRecommendNews());
				visits.setPatient(list);
				
				results.setData(visits);
				results.setStatus("0");
				return results;
			}
//		} catch (Exception e) {
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			results.setStatus("1");
//			return results;
//		}
	}

	@Transactional
	@Override
	public Results<List<DoctorAndPatient>> DoctorPatientList(String title) {
		
		Results<List<DoctorAndPatient>> results=new Results<List<DoctorAndPatient>>();
		
		try {
			
			List<DoctorAndPatient> list=doctorsDao.selectFromOffices(null,title);
			
			for (DoctorAndPatient doctorAndPatient : list) {
				
				List<Doctors> doctorList=doctorsDao.selectDoctorId(doctorAndPatient.getPatientId());
					
				doctorAndPatient.setList(doctorList);
				}
			results.setData(list);
			results.setStatus("0");
			return results;
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				results.setStatus("1");
				return results;
			}
	}
	
}
