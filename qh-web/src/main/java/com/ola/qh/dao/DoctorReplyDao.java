package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.Reply;
import com.ola.qh.entity.UserLikes;

public interface DoctorReplyDao {
	
	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
	
	public int insertLikes(
			@Param("id")String id,
			@Param("userId")String userId,
			@Param("productId")String productId,
			@Param("addtime")Date addtime);
	
	
	public UserLikes singleLikes(@Param("userId")String userId,
			@Param("productId")String productId);
	
	public List<Reply> replyList(
			@Param("patientId")String patientId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
	public int replyListCount(@Param("patientId")String patientId);
	
	public Reply replySingle(@Param("id")String id);
	
	/*public List<DoctorsVo> doctorReplyList(@Param("patientId")String patientId);
	
	public List<Reply> listByIds(
			@Param("patientId") String patientId,
			@Param("doctorId") String doctorId);*/
	
	public int insertReply(Reply dr);
	
/*	public Integer existDoctor(@Param("doctorId") String doctorId);
	
	public Integer existPatient(@Param("patientId") String patientId);*/
	
	public int updateReply(
			@Param("likes")int likes,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
}
