package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserLoginDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserCode;
import com.ola.qh.entity.UserLogin;
import com.ola.qh.entity.UserTypeSubclass;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName:
 * @Description: 用户注册成功时，保存用户信息，注册账本，保存设备信息 用户登录时，保存设备信息
 * @author guozihan
 * @date
 *
 */

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBookDao userbookDao;
	@Autowired
	private UserLoginDao userloginDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<User> saveUsers(User user, HttpServletRequest request) {

		Results<User> result = new Results<User>();
		try {
			// String verification =
			// request.getSession().getAttribute(user.getMobile()).toString();
			UserCode uc = userDao.singleCode(user.getMobile());

			if (uc.getCode().equals(user.getVerification())) {

				User existMobile = userDao.singleUser(null, user.getMobile());
				User users = new User();

				if (existMobile != null) {
					result.setStatus("1");
					result.setMessage("手机号已存在");
					return result;
				}
				user.setAddtime(new Date());
				user.setId(KeyGen.uuid());
				userDao.saveUser(user);
				// userDao.loginUser(user.getMobile(), user.getPassword());

				users.setId(user.getId());
				users.setMobile(user.getMobile());

				UserBook userbook = new UserBook();
				userbook.setId(KeyGen.uuid());
				userbook.setUserId(user.getId());
				userbook.setCanWithdraw(BigDecimal.ZERO);
				userbook.setAddtime(new Date());
				userbookDao.saveUserBook(userbook);

				UserLogin userlogin = new UserLogin();
				userlogin.setId(KeyGen.uuid());
				userlogin.setUserId(user.getId());
				userlogin.setDeviceId(user.getDeviceId());
				userlogin.setDeviceName(user.getDeviceName());
				userlogin.setDeviceToken(user.getDeviceToken());
				userlogin.setDeviceType(user.getDeviceType());
				userlogin.setAddtime(new Date());
				userlogin.setToken(KeyGen.uuid());
				userloginDao.saveUserLogin(userlogin);
				String nickname = users.getMobile().substring(7);
				users.setNickname(nickname);
				users.setToken(userlogin.getToken());

				// 向user_type_subclass表添加
				UserTypeSubclass userTypeSubclass = new UserTypeSubclass();
				userTypeSubclass.setId(KeyGen.uuid());
				userTypeSubclass.setUserId(user.getId());
				userTypeSubclass.setCourseTypeSubclassName(user.getCourseTypeSubclassName());
				userTypeSubclass.setAddTime(new Date());
				userDao.insertUserTypeSubclass(userTypeSubclass);
				// 返回一下选择的专业名
				users.setCourseTypeSubclassName(user.getCourseTypeSubclassName());

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
	public Results<User> loginUser(UserLogin userlogin, HttpServletRequest request) {
		Results<User> results = new Results<User>();
		try {

			User user = new User();

			user = userDao.singleUser(null, userlogin.getMobile());
			if (user == null) {
				results.setMessage("该手机号未注册");
				results.setStatus("1");
				return results;
			}
			if ((userlogin.getVerification() == null || "".equals(userlogin.getVerification()))
					&& (userlogin.getPassword() == null || "".equals(userlogin.getPassword()))) {
				results.setMessage("验证码和密码不能同时为空");
				results.setStatus("1");
				return results;
			}

			if (userlogin.getVerification() != null && !"".equals(userlogin.getVerification())) {
				// String verification =
				// request.getSession().getAttribute(userlogin.getMobile()).toString();
				UserCode uc = userDao.singleCode(userlogin.getMobile());

				if (!userlogin.getVerification().equals(uc.getCode())) {
					results.setMessage("验证码输入有误");
					results.setStatus("1");
					return results;
				}

			}
			if (userlogin.getPassword() != null && !"".equals(userlogin.getPassword())) {
				if (!user.getPassword().equals(userlogin.getPassword())) {
					results.setMessage("密码错误");
					results.setStatus("1");
					return results;
				}
			}

			UserLogin ulold = userloginDao.selectUserLogin(user.getId(), null);
			if (ulold != null) {
				userlogin.setUserId(user.getId());
				userlogin.setToken(KeyGen.uuid());
				userlogin.setUpdatetime(new Date());
				user.setToken(userlogin.getToken());
				userloginDao.updateUserLogin(userlogin);
			} else {
				UserLogin newul = new UserLogin();
				newul.setDeviceId(userlogin.getDeviceId());
				newul.setDeviceName(userlogin.getDeviceName());
				newul.setDeviceToken(userlogin.getDeviceToken());
				newul.setDeviceType(userlogin.getDeviceType());
				newul.setAddtime(new Date());
				newul.setId(KeyGen.uuid());
				newul.setUserId(user.getId());
				newul.setToken(KeyGen.uuid());
				user.setToken(newul.getToken());
				userloginDao.saveUserLogin(newul);
			}

			// 1.根据userid查询user_type_subclass表
			UserTypeSubclass userTypeSubclass = userDao.selectByUserId(user.getId());
			UserTypeSubclass newUserTypeSubclass = null;
			if (userTypeSubclass == null) {
				newUserTypeSubclass = new UserTypeSubclass();
				newUserTypeSubclass.setId(KeyGen.uuid());
				newUserTypeSubclass.setUserId(user.getId());
				newUserTypeSubclass.setCourseTypeSubclassName(userlogin.getCourseTypeSubclassName());
				newUserTypeSubclass.setAddTime(new Date());
				userDao.insertUserTypeSubclass(newUserTypeSubclass);
			} else {
				// 根据ID查询表中专业名
				UserTypeSubclass userTypeSubclass2 = userDao.selectByUserId(user.getId());
				if (userlogin.getCourseTypeSubclassName() != null && !userlogin.getCourseTypeSubclassName()
						.equals(userTypeSubclass2.getCourseTypeSubclassName())) {
					// 不一致 进行更新操作
					Date updateTime = new Date();
					userDao.updateNameById(user.getId(), userlogin.getCourseTypeSubclassName(), updateTime);
					user.setCourseTypeSubclassName(userlogin.getCourseTypeSubclassName());
				} else {
					user.setCourseTypeSubclassName(userlogin.getCourseTypeSubclassName());
				}
				user.setPassword(null);
				results.setStatus("0");
				results.setData(user);
				return results;
			}

			user.setPassword(null);
			user.setCourseTypeSubclassName(userlogin.getCourseTypeSubclassName());
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
	public User sinleUser(String userId, String mobile) {

		return userDao.singleUser(userId, mobile);

	}

	@Override
	public Results<User> existUser(String token) {

		Results<User> result = new Results<User>();
		UserLogin ul = userloginDao.selectUserLogin(null, token);
		if (ul == null) {
			result.setStatus("1");
			result.setMessage("登录过期,请先登录~");
			return result;
		}
		User user = userDao.singleUser(ul.getUserId(), null);
		if (user != null) {
			user.setToken(token);
			result.setStatus("0");
			result.setData(user);
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
			// user2.setPassword(user.getPassword1());
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

	@Override
	public int insertCode(UserCode uc) {
		// TODO Auto-generated method stub
		return userDao.insertCode(uc);
	}

	@Override
	public UserCode singleCode(String mobile) {
		// TODO Auto-generated method stub
		return userDao.singleCode(mobile);
	}

	@Override
	public int updateCode(String code, String mobile) {
		// TODO Auto-generated method stub
		return userDao.updateCode(code, mobile);
	}

	@Override
	public UserBook singleUserBook(String userId) {
		// TODO Auto-generated method stub
		return userbookDao.singleUserBook(userId);
	}

	/**
	 * 登录验证
	 */
	@Override
	public Integer selectByMobileAndPassword(String mobile, String password, HttpServletRequest request) {
		Integer countInteger = 1;
		User user = userDao.loginUser(mobile, password);
		if (user != null) {
			countInteger = 0;
			request.getSession().setAttribute("username", mobile);
			request.getSession().setAttribute("islogin", "1");
		}
		return countInteger;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<User> saveUser(User user, HttpServletRequest request) {

		Results<User> result = new Results<User>();
		try {
			// String verification =
			// request.getSession().getAttribute(user.getMobile()).toString();
			UserCode uc = userDao.singleCode(user.getMobile());

			if (uc.getCode().equals(user.getVerification())) {

				User existMobile = userDao.singleUser(null, user.getMobile());
				User users = new User();

				if (existMobile != null) {
					result.setStatus("1");
					result.setMessage("手机号已存在");
					return result;
				}
				user.setAddtime(new Date());
				user.setId(KeyGen.uuid());
				userDao.saveUser(user);
				// userDao.loginUser(user.getMobile(), user.getPassword());

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
	public Results<String> loginAgainUser(String courseTypeSubclassName, String userId) {
		Results<String> results = new Results<String>();
		// 传过来的userid是token 需要转换
		UserLogin userLogin = userloginDao.selectUserLogin(null, userId);

		// 1.根据userid查询user_type_subclass表
		UserTypeSubclass userTypeSubclass = userDao.selectByUserId(userLogin.getUserId());
		UserTypeSubclass newUserTypeSubclass = null;
		if (userTypeSubclass == null) {
			newUserTypeSubclass = new UserTypeSubclass();
			newUserTypeSubclass.setId(KeyGen.uuid());
			newUserTypeSubclass.setUserId(userLogin.getUserId());
			newUserTypeSubclass.setCourseTypeSubclassName(courseTypeSubclassName);
			newUserTypeSubclass.setAddTime(new Date());
			userDao.insertUserTypeSubclass(newUserTypeSubclass);
		} else {
			// 根据ID查询表中专业名
			UserTypeSubclass userTypeSubclass2 = userDao.selectByUserId(userLogin.getUserId());
			if (userTypeSubclass2.getCourseTypeSubclassName() == null
					|| !userTypeSubclass2.getCourseTypeSubclassName().equals(courseTypeSubclassName)) {
				// 不一致 进行更新操作
				Date updateTime = new Date();
				userDao.updateNameById(userLogin.getUserId(), courseTypeSubclassName, updateTime);
			}
			results.setStatus("0");
			results.setData(courseTypeSubclassName);
			return results;
		}
		results.setStatus("0");
		results.setData(newUserTypeSubclass.getCourseTypeSubclassName());
		return results;
	}

}
