package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public Results<String> doctorsSaveUpdate(Doctors d);/////医生的保存和修改
	
	/////医生的集合

	public List<Doctors> listDoctors(
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("address")String address,
			@Param("professional")String professional,
			@Param("offices")String offices,
			@Param("name")String name);

	
	public Doctors singleDoctors(String id,String userId,String islimit,int page);
	
	public Results<String> patientSaveUpdate(DoctorPatient dp);
	
	public List<DoctorPatient> listPatient(String userId,int pageNo,int pageSize,String issolve,String doctorId);
	
	public DoctorPatient singlePatient(String id);
}
