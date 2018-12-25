package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.DoctorReply;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorReplyVo;


public interface IDoctorReplyService {

	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
	
	public Results<DoctorReplyVo> singleReply(String patientId,String userId);
	
	public Results<String> insertReply(DoctorReply dr); 
}
