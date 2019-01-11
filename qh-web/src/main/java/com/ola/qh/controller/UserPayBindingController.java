package com.ola.qh.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserPayBinding;
import com.ola.qh.service.IUserPayBindingService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName: UserPayBindingController
 * @Description: 用户绑定的支付账号的增删改查
 * @author guozihan
 * @date 2018/12/7
 *
 */
@RestController
@RequestMapping(value = "/api/userpaybinding")
public class UserPayBindingController {

	@Autowired
	private IUserPayBindingService userPayBindingService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<UserPayBinding> selectUserPayBinding(@RequestParam(name = "userId", required = true) String userId) {

		Results<UserPayBinding> results = new Results<UserPayBinding>();

		UserPayBinding select = userPayBindingService.selectUserPayBinding(userId);
		if (select == null) {
			results.setMessage("显示错误");
			results.setStatus("1");
			return results;
		}
		results.setData(select);
		results.setStatus("0");
		return results;

	}

	/**
	 * 通过userId查用户是否已经绑定过了
	 * <p>Title: weixinBinding</p>  
	 * <p>Description: </p>  
	 * @param userId
	 */
	@RequestMapping("/weixin")
	public void weixinBinding(@RequestParam(name="userId",required=true)String userId){
		
	}
	
	
	
	/***
	 * 支付宝的绑定
	 * <p>Title: saveUserPayBinding</p>  
	 * <p>Description: </p>  
	 * @param userpaybinding
	 * @return
	 */
	@RequestMapping(value = "/aliaccount", method = RequestMethod.POST)
	public Results<String> saveUserPayBinding(@RequestBody UserPayBinding userpaybinding) {

		Results<String> results = new Results<String>();

		if (userpaybinding.getUserId() == null || "".equals(userpaybinding.getUserId())) {
			results.setStatus("1");
			results.setMessage("缺少用户ID");
			return results;
		}
		UserPayBinding exist=userPayBindingService.existUserPayBinding(userpaybinding.getUserId());
		
		if(exist!=null){
			userpaybinding.setUpdatetime(new Date());
			int update = userPayBindingService.updateUserPayBinding(userpaybinding);

			if (update <= 0) {
				results.setMessage("操作有误");
				results.setStatus("1");
				return results;
			}
			results.setStatus("0");
			return results;
			
			
		}
		if(userpaybinding.getAliaccount()==null){
			
			results.setStatus("1");
			results.setMessage("支付宝的账号不能为空");
			return results;
		}
		if(userpaybinding.getRealname()==null){
			
			results.setStatus("1");
			results.setMessage("缺少真实姓名");
			return results;
		}
		userpaybinding.setId(KeyGen.uuid());
		userpaybinding.setAddtime(new Date());
		int select = userPayBindingService.saveUserPayBinding(userpaybinding);

		if (select <= 0) {
			results.setMessage("添加错误");
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserPayBinding(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete = userPayBindingService.deleteUserPayBinding(id);
		if (delete <= 0) {
			results.setMessage("删除错误");
			results.setStatus("1");
			return results;
		}

		results.setStatus("0");
		return results;

	}
}
