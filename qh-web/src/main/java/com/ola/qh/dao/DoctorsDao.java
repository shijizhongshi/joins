package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;

public interface DoctorsDao {

	public int insertPatient(DoctorPatient dp);
	
	public List<DoctorPatient> listPatient(
			@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	
	
	
}
