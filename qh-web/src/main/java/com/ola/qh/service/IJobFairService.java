package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.JobFair;

public interface IJobFairService {

	
	public List<JobFair> selectJob(String id,String userId,String company,String category,String education,
			String experience,String salaryRangeMin,String salaryRangeMax,String position,int pageNo,int pageSize);
	
	public int insertJobApply(JobFair jobFair);
	
	public int insertJobFair(JobFair jobFair);
	
	public int updateJobFair(JobFair jobFair);
	
	public int deleteJobFair(String id);
}
