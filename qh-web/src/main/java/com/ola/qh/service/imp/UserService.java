package com.ola.qh.service.imp;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserbookDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.Userbook;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserbookDao userbookDao;
	
	@Transactional
	@Override
	public Results<User> saveUsers(User user,HttpServletRequest request) {
		
		Results<User> result=new Results<User>();
		try {
			String verification = request.getSession().getAttribute(user.getMobile()).toString();
			if (verification.equals(user.getVerification())) {

				User existMobile = userDao.existMobileUser(user.getMobile());
				User users=new User();
				
				if (existMobile!=null) {
					result.setStatus("1");
					result.setMessage("手机号已存在");
					return result;
				}
					user.setAddtime(new Date());
					user.setId(KeyGen.uuid());
					
					int num = userDao.saveUser(user);
					users.setId(user.getId());
					users.setMobile(user.getMobile());
					
					Userbook userbook=new Userbook();
					userbook.setId(KeyGen.uuid());
					userbook.setUserId(user.getId());
					userbook.setAddtime(new Date());
					
					userbookDao.saveUserbook(userbook);
					result.setData(users);
					result.setStatus("0");
					return result;
				}
				
			result.setStatus("1");
			result.setMessage("验证码有误");
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}
		
		
		
	}

	@Override
	public User loginUser(String mobile, String password) {
		
		return userDao.loginUser(mobile, password);
	}

	@Override
	public User existMobileUser(String mobile) {
		
		return userDao.existMobileUser(mobile);
	}

	@Override
	public int updateUser(String nickname, String headimg, String id) {
		
		return userDao.updateUser(nickname, headimg, id);
	}

	

	

	
	
}
