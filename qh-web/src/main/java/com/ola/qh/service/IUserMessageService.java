package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserMessage;

public interface IUserMessageService {

public List<UserMessage> listByType(String userId,int types);
	
	public List<UserMessage> list(String userId,int pageNo,int pageSize,int types);
}
