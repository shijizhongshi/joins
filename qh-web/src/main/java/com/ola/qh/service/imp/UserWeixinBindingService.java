package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserWeixinBindingDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.service.IUserService;
import com.ola.qh.service.IUserWeixinBindingService;
import com.ola.qh.util.Results;

@Service
public class UserWeixinBindingService implements IUserWeixinBindingService{

	@Autowired
	private UserWeixinBindingDao userBindingDao;
	@Autowired
	private IUserService userService;
	
	@Override
	public Results<String> existUserBinding(String userId) {
		Results<String> result=new Results<String>();
		Results<User> userResult = userService.existUser(userId);
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		int count=userBindingDao.existUserBinding(userId);
		result.setStatus("0");
		result.setData(userResult.getData().getId());
		result.setCount(count);
		return result;
	}

	@Override
	public int saveUserBinding(UserWeixinBinding userbinding) {
		
		return userBindingDao.saveUserBinding(userbinding);
	}
	
}
