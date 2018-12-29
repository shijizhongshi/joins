package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.PromptMessageDao;
import com.ola.qh.entity.PromptMessage;
import com.ola.qh.service.IPromptMessageService;

@Service
public class PromptMessageService implements IPromptMessageService{

	@Autowired
	private PromptMessageDao promptMessageDao;

	@Override
	public List<PromptMessage> selectPatient(String userId,int issolve) {
		
		return promptMessageDao.selectPatient(userId,issolve);
	}

	@Override
	public List<PromptMessage> selectDoctorReplyPatient(String id) {
		
		return promptMessageDao.selectDoctorReplyPatient(id);
	}

	
}
