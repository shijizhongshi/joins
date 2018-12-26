package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorVisitsDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.service.IDoctorVisitsService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorVisitsVo;

@Service
public class DoctorVisitsService implements IDoctorVisitsService{

	@Autowired
	private DoctorVisitsDao doctorVisitsDao;
	
	@Autowired
	private DoctorsDao doctorsDao;
	
	@Autowired
	private NewsDao newsDao;

	@Autowired
	private DoctorReplyDao doctorReplyDao;
	@Transactional
	@Override
	public Results<String> selectDoctorVisits(String offices) {
		
		Results<String> results=new Results<String>();
		try {
			
			DoctorVisitsVo visits=new DoctorVisitsVo();
			
			visits.setDoctor(doctorsDao.listRecommendDoctor());
			visits.setNews(newsDao.selectRecommendNews());
			
			List<Doctors> listfromoffices=doctorsDao.selectFromOffices(offices);
			
			for (Doctors doctors : listfromoffices) {
				doctorReplyDao.replyPatientList(doctors.getId(), 0, 6);
			}
			
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	
}
