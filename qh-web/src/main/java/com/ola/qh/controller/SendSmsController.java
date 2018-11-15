package com.ola.qh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.ISendSmsService;
import com.ola.qh.util.RBuilder;
import com.ola.qh.util.Results;

/**
 * 
 * @ClassName: SendSmsController
 * @Description: 发送验证码
 * @author guoyuxue
 * @date 2018年11月15日
 *
 */
@RestController
@RequestMapping("/api/sms")
public class SendSmsController {

	@Autowired
	private ISendSmsService sendSmsService;

	/**
	 * 注册时发送手机号
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/sendmobile")
	public Results<String> sendSms(@RequestParam(required = true, name = "mobile") String mobile) {

		Results<String> result = new Results<String>();
		Map<String, String> map = new HashMap<String, String>();
		///// map放code之外的参数
		sendSmsCommon(mobile, "SMS_151230053", map);
		return result;
	}

	/**
	 * 注册的时候和修改密码的时候发送短信的模板不一样 取出相同部分的操作来~
	 * 
	 * @param mobile
	 * @param templateCode
	 * @param map
	 * @return
	 */
	public Results<String> sendSmsCommon(String mobile, String templateCode, Map<String, String> map) {

		String code = new String(new RBuilder().length(4).hasletter(false).next());
		map.put("code", code);
		Results<String> result = sendSmsService.sendSms(mobile, templateCode, map);
		return result;
	}

}
