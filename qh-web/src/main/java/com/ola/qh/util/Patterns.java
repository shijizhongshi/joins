package com.ola.qh.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Patterns {
	

	//////新闻分页的个数
    public static final int zupageSize = 12;
    
    public static final String INTERNAL_MOBILE_PATTERN = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$";

    public static final String IDCARD_PATTERN="^[1-8][0-7]{2}\\d{3}([12]\\d{3})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx])$";
    
    public static String sfTime(Date time){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
    	return sf.format(time);
    }
    
    public static String sfDetailTime(Date time){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    	return sf.format(time);
    }
    
    public static final String appId="wxa13c67ede13ea9a7";
    
    public static final String appSecret="f59e578884a7c5992cf2074953610853";
    
    
    /////授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
    ///////   http://api.zhongshicc.com/api/wx/web/auth/base
    public static final String redirectUri="http%3a%2f%2fapi.zhongshicc.com%2fapi%2fwx%2fweb%2fauth%2fbase";/////回调地址
    
    public static final String getAccessToken="https://api.weixin.qq.com/sns/oauth2/access_token";
    
    public static final String userinfo="https://api.weixin.qq.com/sns/userinfo";
    
    
    //////////////////七牛云短视频的参数
    public static final String ACCESS_KEY="CvXk1F9bBZz8tmPz_d-r1yNUxNUPTc-r6Xa3jGny";
    
    public static final String SECRET_KEY="8Y27T80B8Q6hr-KtO_sSFJneI6uOg21CyhCp1LRA";
    
    public static final String BUCKET="video";
    
    //////回调的参数
    public static final String callback="http://api.zhongshicc.com/api/qiniu/callbacked";
    
    
}
