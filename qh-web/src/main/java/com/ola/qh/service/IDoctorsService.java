package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public Results<String> doctorsSaveUpdate(Doctors d);/////医生的保存和修改
	
	/////医生的集合
	public List<Doctors> listDoctors(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public Doctors singleDoctors(String id,String userId,String islimit);
	
	public Results<String> patientSaveUpdate(DoctorPatient dp);
	
	public List<DoctorPatient> listPatient(
			@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public DoctorPatient singlePatient(String id);
}
