package com.ola.qh.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.service.IUserWeixinBindingService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
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
@RequestMapping(value = "/api/userbinding")
public class UserWeixinBindingController {

	@Autowired
	private IUserWeixinBindingService userPayBindingService;

	/**
	 * 通过userId查用户是否已经绑定过了
	 * <p>Title: weixinBinding</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @throws IOException 
	 */
	@RequestMapping("/weixin")
	public void weixinBinding(@RequestParam(name="userId",required=true)String userId,HttpServletResponse rep) throws IOException{
		
		/////直接让他出来授权登录的页面
		rep.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Patterns.appId+"&redirect_uri="+Patterns.redirectUri+"&response_type=code&scope=snsapi_userinfo&state="+userId+"#wechat_redirect");
		
	}
	
	/**
	 * 如果有的话
	 * @param userId
	 * @return
	 */
	@RequestMapping("/exist")
	public Results<String> exist(@RequestParam(name="userId",required=true)String userId){
		
		Results<String> result=new Results<String>(); 
		result = userPayBindingService.existUserBinding(userId);
		if(result.getCount()>0){
			result.setStatus("0");
			return result;
		}else{
			result.setStatus("1");
			return result;
		}
		
	}
	
}
