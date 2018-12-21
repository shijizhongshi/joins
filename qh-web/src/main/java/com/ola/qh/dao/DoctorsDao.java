package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.entity.Doctors;

public interface DoctorsDao {

	/////保存患者的信息
	public int insertPatient(DoctorPatient dp);
	//////患者信息的集合
	public List<DoctorPatient> listPatient(
			@Param("userId")String userId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int updatePatient(DoctorPatient dp);
	
	public int insertPatientImg(DoctorPatientImg dpi);
	
	public int deletePatientImg(String id);
	
	public List<DoctorPatientImg> listPatientImg(String patientId);///数据库中的数据参数
	
	public int doctorsCount(String userId);
	
	public int doctorsInsert(Doctors d);
	/////分页展示用户的信息
	public List<Doctors> listDoctor(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
	public int updateDoctors(Doctors d);////修改用户的基本信息
	
	public Doctors singleDoctors(String id,String userId,String islimit);/////查用户的详情
}
