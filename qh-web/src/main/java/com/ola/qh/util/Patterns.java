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
    
    
    /*
     * 本地测试账号
     * public static final String appId="wx3c4c8650c28b1ee2";
    
    public static final String appSecret="f4e9dab1e3144932581382c1de4a7a1f";*/
    
    /////授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
   ////    http://api.zhongshicc.com/api/wx/web/auth/base  urlEncode 
   public static final String redirectUri="http%3a%2f%2fapi.zhongshicc.com%2fapi%2fwx%2fweb%2fauth%2fbase";/////回调地址
    
    
    
    
    ///////测试  http://995c0ae3.ngrok.io/api/wx/web/auth/base
    
  ///  public static final String redirectUri="http%3a%2f%2f045e5193.ngrok.io%2fapi%2fwx%2fweb%2fauth%2fbase";/////回调地址
    
    

    public static final String getAccessToken="https://api.weixin.qq.com/sns/oauth2/access_token";
    
    public static final String userinfo="https://api.weixin.qq.com/sns/userinfo";
    
    
    //////////////////七牛云短视频的参数
    public static final String ACCESS_KEY="CvXk1F9bBZz8tmPz_d-r1yNUxNUPTc-r6Xa3jGny";
    
    public static final String SECRET_KEY="8Y27T80B8Q6hr-KtO_sSFJneI6uOg21CyhCp1LRA";
    
    public static final String BUCKET="video";
    
    //////回调的参数
    public static final String callback="http://api.zhongshicc.com/api/qiniu/callbacked";
    
    
    public static final String wxappId="wxfc7f0bca7a44cbb8";/////公众号的appid
	public static final String wxmchId="1526334321";////商户号

	public static final  String wxsignkey="6d2fc165d722531a87df0f63bc639685";
    
	public static final String cert_path="/home/apiclient_cert.p12";
    
	
	
    public static final String ALI_APPID="2019022063258258";
    
    public static final String  ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArijI7FpHDMwnJ6951LlfsuWBhSWUUn+F214shLH9IIP6aqDV+fJM7B6exjktb1l40m1eelP3j2epThsnOjAhi+Agw6pen0HnuCQgjTVm0Cz3BfAdXUuJeLn8RONwUXybdgpWWdO/JiLt+Ge7DY8CYi02hm8rnb8+Gq2WfgzzT9KBEVsoCnne2yHgpEPV9LReC7l6v4to4omGqzkZnf/TL5gjXEPSV8HwnMmvi13aaoEGxb6MNrw5c5N7X+9EoGcWAbovwiby53D5gnlt48c8wM/auo2pVIFHMoCvl2NuTrZEZP7DQdMdYt/s0ktIQmuPTcO1pqhI5pGE2lmCivS4/wIDAQAB";
    
    public static final String ALIPAY_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQZyX/MGtHylSjPFnLlzitRRDl/AojrJXVJTc4fG3CevphTDuGbGdQhy/Tv42PonaKcy90GsJ2ciG7vOCoTtev/g1MtNPBmezBFm2wgV9eGFEnXJcHJ4QXwppPYPR6t8Jm1NlCGEGydDsKqYZnOGExO6OsvXUXu1JN2ocqtz4Qu9mJQbe/etSJWcXmEWo7+sg3Ov8m9bF2qTKueWVivD/SjaMVpvWDwIDWNsxanOkg1JRRLo1kHXngFJAkZQ6sgCKIy6UMyrffpv3UcJesH8Xl9LSaWJvQZE5BcLz6LM8r8EVh7SM51jMt2mbQv+QlbzPOVTyVez6we+qhEXx5iDh7AgMBAAECggEALGVaHEQRp6fAL9OdM+fRSAxE7sB3iZJFGpvvh1FSGYnfssxSPLjVZr+5Z5ur9S4YzeYt/78+bFYYovSmCXj7/SLZiezKcepWDONNZAfSf0ZwpO5ljJCMnxV4hdEkORqIDT+BcD2u7SOpB1UpelCuKQnR/4jcGCjCXCdEiVB/J3tzKp20ep1Mfb6cx626TIKGJ5z7Kgd6kmTgzHudVAAc7MQcxPzNlIenSqg9utu2EGGwDX/Ua0eaJ16mPkF8+kWAMYpEwz+Oz1royAInKKuS1sL0XE/y6+C3tSSntdK6bhlGsYprKaDYWqz9CQvJQLx+N3pyjd91qjQZSQsa8Gu8YQKBgQDMaikRs5pVYTKGnKLFMkkCAXoTcGB8evOmulYhs9wDsrPGp77AhhTBCqsBCdk4BkiwJk64wlPgpZe8vWWzYh3pAjvTjR/cO+2d0KnJvJjdkIyo5Bef3TDyxQTBTDxBz1nZF7m6cOfKvqdKWjN9ztKwJ+nKvWMGAh8zwzP25PYx6wKBgQC02AlzqmeD23ZoVYiw4FC8Lwfex29GHJW65TQm9hIxd1bs+G0ZAm6zyNvE+WjZfijVu1kq7OA1Y+WWFmkwbP/q5EiDGaOxAGNE6lDfDU/MSeXkARwcPIpbK491U7nj/SlWad+yk7IfDexVWC9XjizSeXJx51DA3HxOagnWmHzfsQKBgQCVZCCPp0rrixV5+4+Zvp5MsPeerUpWryTSfOvlxaTNrxRj4sQtv8/7JOjE4YW2xCDrW5O8w4i5bUvvzBs/dj2VxOQkP8dBFSsbYxwzK3ossy+ZXtlFJEpZolNPUX/YOMnZNtWtxEYijiOE21c2YqjnxMPAP5jXE0XqdobLsZmb8QKBgEQScfRc91wV1wtM7wgNV7aOSXsdPRB6IA5aynfD9nMyRcErff2FAeCgwcDyVPpRV9OkEttqkLboJHqLcwrsbKShS341v30X9UXyoYBkhV4Tjvp1RHyIeVf6HKUAYB+motHJRo/0sn6oAgbaC+/8L3kGiALCHI6MMCpgFdX2TkLxAoGAes5e566efL6++5E5emZAIF50h3jx4yFE9lkvRn2JEWdkHvUiuh5xSBXgUTiOqS1JOCOEck4yEGI4ZlzfXNXsJs+T5RWmw0X0WKyJJ1AlNwKvCZMYwHpdryh7iTbz8AXjk7p7G+UDW+MgP8Ecv0dM0wVU1J2kIWS2rlxj/190ODs=";

}
