package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.PromptMessage;

public interface IPromptMessageService {

	public List<PromptMessage> selectPatient(String userId,int issolve);
	
	public List<PromptMessage> selectDoctorReplyPatient(String id);
	
	
}
