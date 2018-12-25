package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.DoctorReply;
import com.ola.qh.vo.DoctorsVo;

public interface DoctorReplyDao {
	
	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
	
	public List<DoctorPatient> replyPatientList(
			@Param("doctorId")String doctorId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public List<DoctorsVo> doctorReplyList(String patientId);
	
	public List<DoctorReply> listByIds(
			@Param("patientId") String patientId,
			@Param("doctorId") String doctorId);
	
	public int insertReply(DoctorReply dr);
	
	public Integer existDoctor(String doctorId);
	
	public Integer existPatient(String patientId);
}
