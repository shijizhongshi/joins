package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorPatientImg;
import com.ola.qh.entity.Doctors;
import com.ola.qh.vo.DoctorAndPatient;

public interface DoctorsDao {

	/////保存患者的信息
	public int insertPatient(DoctorPatient dp);
	//////患者信息的集合
	public List<DoctorPatient> listPatient(
			@Param("userId")String userId,
			@Param("category")String category,
			@Param("searchName")String searchName,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	//////修改问题的点赞量
	public int updatePatient(
			@Param("likes")int likes,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
	
	public DoctorPatient singlePatient(@Param("id") String id);
	
	public int insertPatientImg(DoctorPatientImg dpi);
	
	public int deletePatientImg(@Param("id")String id);
	
	public List<DoctorPatientImg> listPatientImg(@Param("patientId")String patientId);///数据库中的数据参数
	
	public int doctorsCount(@Param("userId") String userId);
	
	public int doctorsInsert(Doctors d);
	/////分页展示用户的信息
	public List<Doctors> listDoctor(
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize,
			@Param("address")String address,
			@Param("professional")String professional,
			@Param("offices")String offices,
			@Param("name")String name);
	
	public Double commentGrade(@Param("doctorId") String doctorId);
	
	public int updateDoctors(Doctors d);////修改用户的基本信息
	
	public Doctors singleDoctors(
			@Param("id")String id,
			@Param("userId")String userId,
			@Param("islimit")String islimit);/////查用户的详情
	
	
	//寻医问药
	/*public List<Doctors> listRecommendDoctor();
	
	public List<DoctorAndPatient> selectFromOffices(@Param("offices")String offices,@Param("title")String title);
	
	public List<Doctors> selectDoctorId(@Param("patientId")String patientId);
	
	public List<DoctorAndPatient> selectPatient(@Param("issolve")int issolve);
	
	//铃铛
	public List<DoctorAndPatient> existReadStatus(@Param("userId")String userId);*/
	
	public String selectId(@Param("userId")String userId);
	
	public List<DoctorPatient> DoctorPatientsList(@Param("topSearch")String topSearch);
}
