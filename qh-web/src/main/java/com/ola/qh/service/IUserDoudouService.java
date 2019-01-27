package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.UserDouDou;
import com.ola.qh.util.Results;

public interface IUserDoudouService {

	public Results<String> insertDoudou(UserDouDou udd);
	
	//////////查询所有的豆豆的来源
	public List<UserDouDou> listDoudou(String userId,int pageNo,int pageSize);
}
