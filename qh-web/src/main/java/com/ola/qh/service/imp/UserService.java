package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
/**
 * 
 * 
* @ClassName: 
* @Description:  用户注册成功时，保存用户信息，注册账本，保存设备信息
* 用户登录时，保存设备信息
* @author guozihan
* @date   
*
 */

@Service
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBookDao userbookDao;
	@Autowired
	private UserLoginDao userloginDao;
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<User> saveUsers(User user,HttpServletRequest request) {
		
		Results<User> result=new Results<User>();
		try {
			String verification = request.getSession().getAttribute(user.getMobile()).toString();
			if (verification.equals(user.getVerification())) {

				User existMobile = userDao.existUser(user.getMobile(),null);
				User users=new User();
				
				if (existMobile!=null) {
					result.setStatus("1");
					result.setMessage("手机号已存在");
					return result;
				}
					user.setAddtime(new Date());
					user.setId(KeyGen.uuid());
					userDao.saveUser(user);
					//userDao.loginUser(user.getMobile(), user.getPassword());
					
					users.setId(user.getId());
					users.setMobile(user.getMobile());
					
					UserBook userbook=new UserBook();
					userbook.setId(KeyGen.uuid());
					userbook.setUserId(user.getId());
					userbook.setAccountMoney(BigDecimal.ZERO);
					userbook.setAddtime(new Date());
					userbookDao.saveUserBook(userbook);
					
					UserLogin userlogin=new UserLogin();
					userlogin.setId(KeyGen.uuid());
					userlogin.setUserId(user.getId());
					userlogin.setDeviceId(user.getDeviceId());
					userlogin.setDeviceName(user.getDeviceName());
					userlogin.setDeviceToken(user.getDeviceToken());
					userlogin.setDeviceType(user.getDeviceType());
					userlogin.setAddtime(new Date());
					userloginDao.saveUserLogin(userlogin);
					
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
	@Transactional
	@Override
	public Results<User> loginUser(UserLogin userlogin,HttpServletRequest request) {
		Results<User> results=new Results<User>();
		try {
			
			if((userlogin.getVerification()==null || "".equals(userlogin.getVerification())) && (userlogin.getPassword()==null || "".equals(userlogin.getPassword()))){
				results.setMessage("验证码和密码不能同时为空");
				results.setStatus("1");
				return results;
			}
			User user = new User();
			if(userlogin.getVerification()!=null && !"".equals(userlogin.getVerification())){
				String verification = request.getSession().getAttribute(userlogin.getMobile()).toString();
				if(!userlogin.getVerification().equals(verification)){
					results.setMessage("验证码输入有误");
					results.setStatus("1");
					return results;
				}
				user = userDao.existUser(userlogin.getMobile(), null);
				if(user==null){
					results.setMessage("该手机号未注册");
					results.setStatus("1");
					return results;
				}
				
			}
			if(userlogin.getPassword()!=null && !"".equals(userlogin.getPassword())){
				user = userDao.loginUser(userlogin.getMobile(), userlogin.getPassword());
				if (user == null) {
					results.setMessage("用户名或密码错误");
					results.setStatus("1");
					return results;
				}
			}
		
			userlogin.setUserId(user.getId());
			userlogin.setUpdatetime(new Date());
			userloginDao.updateUserLogin(userlogin);
			
			user.setDeviceId(userlogin.getDeviceId());
			user.setDeviceName(userlogin.getDeviceName());
			user.setDeviceToken(userlogin.getDeviceToken());
			user.setDeviceType(userlogin.getDeviceType());
			
			results.setStatus("0");
			results.setData(user);
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("登录失败");
			return results;
		}
	}

	@Override
	public User existMobileUser(String mobile) {
		
		return userDao.existUser(mobile,null);
		
		
	}
	
	
	
	@Override
	public Results<String> existUser(String userId) {
		
		Results<String> result = new Results<String>();
		User user = userDao.existUser(null,userId);
		if(user!=null){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("这个用户不存在");
		return result;
		
	}

	@Override
	public int updateUser(User user) {
		user.setUpdatetime(new Date());
		return userDao.updateUser(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<User> updatePassword(User user) {

		Results<User> results = new Results<User>();
		try {
			String mobile = user.getMobile();
			String password = user.getPassword();
			User user1 = userDao.loginUser(mobile, password);
			if (user1 == null) {
				results.setMessage("用户名或密码错误");
				results.setStatus("1");
				return results;
			}

			User user2 = new User();
			user2.setId(user1.getId());
			//user2.setPassword(user.getPassword1());
			userDao.updatePassword(user2);
			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("密码修改失败");
			return results;
		}

	}

}
