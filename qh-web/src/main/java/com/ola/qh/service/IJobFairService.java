package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.JobFair;

public interface IJobFairService {

	
	public List<JobFair> selectJob(String id,String userId,String company,String education,
			String experience,String salaryRange,String position,int pageNo,int pageSize);
	
	public int insertJobFair(JobFair jobFair);
	
	public int updateJobFair(JobFair jobFair);
	
	public int deleteJobFair(String id);
}
