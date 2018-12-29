package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.PromptMessage;

public interface PromptMessageDao {

	public List<PromptMessage> selectPatient(@Param("userId")String userId,@Param("issolve")int issolve);
	
	public List<PromptMessage> existReadStatus(@Param("userId")String userId);
	
	public List<PromptMessage> selectDoctorReplyPatient(@Param("id")String id);
}
