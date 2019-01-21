package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserMessageDao;
import com.ola.qh.entity.UserMessage;
import com.ola.qh.service.IUserMessageService;
@Service
public class UserMessageService implements IUserMessageService{

	@Autowired
	private UserMessageDao messageDao;
	
	
	@Override
	public List<UserMessage> listByType(String userId,int types) {
		// TODO Auto-generated method stub
		return messageDao.listByType(userId,types);
	}

	@Override
	public List<UserMessage> list(String userId, int pageNo, int pageSize,int types) {
		// TODO Auto-generated method stub
		return messageDao.list(userId, types, pageNo, pageSize);
	}

}
