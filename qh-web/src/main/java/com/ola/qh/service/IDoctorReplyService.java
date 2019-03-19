package com.ola.qh.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.Reply;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorReplyVo;


public interface IDoctorReplyService {

	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
	
	/*public Results<DoctorReplyVo> singleReply(String patientId,String userId);*/
	
	public Results<String> insertReply(Reply dr); 
	
	public Results<List<Reply>> listReply(String patientId,int pageNo,int pageSize,String userId); 
	
	public Results<String> updateReply(String id,String userId);
}
