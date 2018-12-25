package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.Doctors;
import com.ola.qh.util.Results;

public interface IDoctorsService {

	public Results<String> doctorsSaveUpdate(Doctors d);/////医生的保存和修改
	
	/////医生的集合
	public List<Doctors> listDoctors(int pageNo,int pageSize,String address,String professional,String offices,String name);
	
	public Doctors singleDoctors(String id,String userId,String islimit,int page);
	
	public Results<String> patientSaveUpdate(DoctorPatient dp);
	
	public List<DoctorPatient> listPatient(String userId,int pageNo,int pageSize,String issolve);
	
	public DoctorPatient singlePatient(String id);
}
