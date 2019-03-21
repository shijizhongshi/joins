package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.JobFair;

public interface JobFairDao {

	
	public List<JobFair> selectJob(@Param("id")String id,@Param("userId")String userId,
			@Param("category")String category,@Param("salaryRangeMin")String salaryRangeMin,
			@Param("salaryRangeMax")String salaryRangeMax,
			@Param("searchName")String searchName,
			@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	///发布求职信息
	public int insertJobApply(JobFair jobFair);
		
		
	///发布招聘信息
	public int insertJobFair(JobFair jobFair);
	
	public int updateJobFair(JobFair jobFair);
	
	public int deleteJobFair(@Param("id")String id);
}
