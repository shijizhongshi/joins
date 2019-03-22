package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.JobFairDao;
import com.ola.qh.entity.JobFair;
import com.ola.qh.service.IJobFairService;

@Service
public class JobFairService implements IJobFairService{

	@Autowired
	private JobFairDao jobFairDao;
	
	@Override
	public List<JobFair> selectJob(String id, String userId, String category, 
			String salaryRangeMin,String salaryRangeMax, String searchName,int pageNo,int pageSize) {
		
		return jobFairDao.selectJob(id, userId, category, salaryRangeMin, salaryRangeMax, searchName, pageNo, pageSize);
	}

	@Override
	public int insertJobFair(JobFair jobFair) {
		
		return jobFairDao.insertJobFair(jobFair);
	}

	@Override
	public int updateJobFair(JobFair jobFair) {
		
		return jobFairDao.updateJobFair(jobFair);
	}

	@Override
	public int deleteJobFair(String id) {
		
		return jobFairDao.deleteJobFair(id);
	}

	@Override
	public int insertJobApply(JobFair jobFair) {
		
		return jobFairDao.insertJobApply(jobFair);
	}

}
