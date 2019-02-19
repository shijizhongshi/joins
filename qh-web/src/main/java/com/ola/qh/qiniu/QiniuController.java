package com.ola.qh.qiniu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.qiniu.util.Auth;
import com.ola.qh.qiniu.util.StringMap;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

import net.sf.json.JSONObject;

/**
 *调取凭证和回调用的
* @ClassName:QiniuController
* @Description:
* @author guoyuxue
* @date:2019年1月22日 
*
 */
@RestController
@RequestMapping("/api/qiniu")
public class QiniuController {

	@Autowired
	private UserVideoDao userVideoDao;
	/**
	 * 调取上传的凭证
	 * @param tp
	 * @return
	 */
	@RequestMapping(value="/uploadToken",method=RequestMethod.GET)
	public Results<String> uploadToken(){
		Results<String> result=new Results<String>();
		    Auth auth = Auth.create(Patterns.ACCESS_KEY, Patterns.SECRET_KEY);
	        StringMap putPolicy = new StringMap();
	        //设置回调地址
	        putPolicy.put("callbackUrl",Patterns.callback);
	        //设置回调参数,android最终从七牛云得到的数据的格式就是这里设置的回调参数
	        putPolicy.put("callbackBody", "{\"mimeType\":\"$(mimeType)\",\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"userId\":\"$(x:userId)\"}");
	        //设置回调返回类型
	        putPolicy.put("callbackBodyType", "application/json");
	        //设置超时
	        long expireSeconds = 3600;
		 String token = auth.uploadToken(Patterns.BUCKET,null, expireSeconds,putPolicy);
		 result.setStatus("0");
		 result.setData(token);
		 return result;
	}
	
	
	
	@RequestMapping(value="/callbacked",method=RequestMethod.POST)
	public @ResponseBody UserVideo classback(HttpServletRequest request, HttpServletResponse response)
			throws Exception{

		 //回调地址
        String callbackUrl =Patterns.callback;
        //定义回调内容的组织格式
        //String callbackBodyType = "application/x-www-form-urlencoded";
        String callbackBodyType = "application/json";
        /** * 这两个参数根据实际所使用的HTTP框架进行获取 */
        //通过获取请求的HTTP头部Authorization字段获得
		 String callbackAuthHeader = request.getHeader("Authorization");
	        //通过读取回调POST请求体获得，不要设置为null
	        byte[] callbackBody = new byte[1024];
	        try {
	            //这里是最重要的，接收byte[]
	            request.getInputStream().read(callbackBody);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //将byte[]转化为字符串
	        String callbackBodyStr = new String(callbackBody);
	        Auth auth = Auth.create(Patterns.ACCESS_KEY, Patterns.SECRET_KEY);
	        //检查是否为七牛合法的回调请求
	        boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
	        if (validCallback) {
	            //继续处理其他业务逻辑
	        	JSONObject obj = JSONObject.fromObject(callbackBodyStr);
	        	UserVideo uv = Json.from(callbackBodyStr, UserVideo.class);
		        System.out.println(obj);
		        uv.setId(KeyGen.uuid());
		        uv.setAddtime(new Date());
		    /*    uv.setVideoUrl(callbackBodyStr);*/
		        /////////只有doctorId医生才可以去上传小视频
		        //uv.setDoctorId(doctorId);
	        	userVideoDao.insert(uv);
	        	return uv;
	        	
	        } else {
	            //这是哪里的请求，被劫持，篡改了吧？
	        	return null;
	        }
	        //将字符串转化为json对象
	        
	        
	        
	}
	
	
	
	
	
	
	
	
	
	
}
