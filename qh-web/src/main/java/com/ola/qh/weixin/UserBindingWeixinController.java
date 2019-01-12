package com.ola.qh.weixin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.service.IUserWeixinBindingService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.weixin.entity.WeixinInfo;
import com.ola.qh.weixin.entity.WeixinOauth2Token;
import com.ola.qh.weixin.handler.Requests;
/**
* @ClassName:UserBindingWeixinController
* @Description:
* @author guoyuxue
* @date:2019年1月12日 
*
 */
@RestController
public class UserBindingWeixinController {

	@Autowired
	private IUserWeixinBindingService userBindingService;
	
	
	/**
	 * 微信回调的方法
	 * @param request
	 * @param resp
	 * @param session
	 * @param code
	 * @param state
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/wx/web/auth/base")
	public void getWeiXinInfo(HttpServletRequest request, 
			HttpServletResponse resp, 
			HttpSession session,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "state") String state) throws Exception{
		
		
		 request.setCharacterEncoding("utf-8");
		 resp.setCharacterEncoding("utf-8");
		
		String userId=state;////把用户的id
		Map<String,String> mapss=new HashMap<String,String>();		
		mapss.put("appId", Patterns.appId);
		mapss.put("appSecret", Patterns.appSecret);
		mapss.put("code",code);
		mapss.put("grant_type", "authorization_code");
		Results<byte[]> rbody = Requests.get(Patterns.getAccessToken, null, mapss);
		byte[] bytess = rbody.getData();
		String body = new String(bytess);
		WeixinOauth2Token tokens = Json.from(body, WeixinOauth2Token.class);
		
		Map<String,String> map=new HashMap<>();
		map.put("access_token",tokens.getAccess_token());
		map.put("openid", tokens.getOpenid());
		map.put("lang", "zh_CN");
		
		Results<byte[]> userinfobody = Requests.get(Patterns.userinfo, null, map);
		byte[] byteuser = userinfobody.getData();
		String bodyuser = new String(byteuser);
		WeixinInfo userInfo = Json.from(bodyuser, WeixinInfo.class);
		
		if(userInfo!=null){
			
			int count = userBindingService.existUserBinding(userId);
			if(count==0){
				UserWeixinBinding userbinding=new UserWeixinBinding();
				userbinding.setHeadimgurl(userInfo.getHeadimgurl());
				userbinding.setOpenId(userInfo.getOpenid());
				userbinding.setUserId(userId);
				userbinding.setId(KeyGen.uuid());
				userbinding.setNickname(userInfo.getNickname());
				userbinding.setAddtime(new Date());
				userBindingService.saveUserBinding(userbinding);
			}
			
		}
		
		
		
		
		
		
		
		
	}
	
}
