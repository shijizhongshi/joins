package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.Reply;
import com.ola.qh.util.Results;


public interface IDoctorReplyService {

	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
	
	/*public Results<DoctorReplyVo> singleReply(String patientId,String userId);*/
	
	public Results<String> insertReply(Reply dr); 
	
	public Results<List<Reply>> listReply(String patientId,int pageNo,int pageSize,String userId); 
	
	public Results<String> updateReply(String id,String userId);
}
