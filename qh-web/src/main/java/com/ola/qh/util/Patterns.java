package com.ola.qh.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Patterns {
	

	//////新闻分页的个数
    public static final int zupageSize = 8;
    
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
    
    
}
