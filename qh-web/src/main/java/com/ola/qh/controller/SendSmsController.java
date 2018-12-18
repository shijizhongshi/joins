package com.ola.qh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserCode;
import com.ola.qh.service.ISendSmsService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
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

	@Autowired
	private IUserService userService;
	
	/**
	 * 注册时发送手机号
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/sendmobile")
	public Results<String> sendSms(
			@RequestParam(required = true, name = "mobile") String mobile,
			@RequestParam(required=true,name="types")String types,
			HttpServletRequest request) {

		Pattern pattern = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
		pattern.matcher(mobile).matches();

		Results<String> result = new Results<String>();

		if (!pattern.matcher(mobile).matches()) {
			result.setStatus("1");
			result.setMessage("手机号格式有误");
			return result;
		}
		if("1".equals(types)){
			User existMobile = userService.sinleUser(null,mobile);
			if (existMobile == null) {
				Map<String, String> map = new HashMap<String, String>();

				///// map放code之外的参数

				return sendSmsCommon(mobile, "SMS_151231966", map, request);
			}
			result.setStatus("1");
			result.setMessage("手机号已存在");
			return result;
		}else if("2".equals(types)){
			Map<String, String> map = new HashMap<String, String>();

			///// map放code之外的参数
			////验证码身份的模板消息
			return sendSmsCommon(mobile, "SMS_151460179", map, request);
		}
		
		result.setStatus("1");
		result.setMessage("请选择消息类别");
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
	public Results<String> sendSmsCommon(String mobile, String templateCode, Map<String, String> map,
			HttpServletRequest request) {

		String code = new String(new RBuilder().length(4).hasletter(false).next());
		map.put("code", code);
		Results<String> result = sendSmsService.sendSms(mobile, templateCode, map);
		UserCode uc = userService.singleCode(mobile);
		if(uc==null){
			UserCode ue = new UserCode();
			ue.setCode(code);
			ue.setAddtime(new Date());
			ue.setMobile(mobile);
			ue.setId(KeyGen.uuid());
			userService.insertCode(ue);
		}else{
			userService.updateCode(code, mobile);
		}
		//request.getSession().setAttribute(mobile, code);
		return result;

	}

}
