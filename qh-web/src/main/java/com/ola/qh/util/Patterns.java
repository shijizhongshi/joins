package com.ola.qh.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.ola.qh.dao.CommentTextDao;
import com.ola.qh.entity.CommentText;
import com.ola.qh.entity.ShopServeType;

public class Patterns {
	
	@Autowired
	private CommentTextDao commentTextDao;

	//////新闻分页的个数
    public static final int zupageSize = 8;
    
    public static final String INTERNAL_MOBILE_PATTERN = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$";

    public static String sfTime(Date time){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
    	return sf.format(time);
    }
    
    public static String sfDetailTime(Date time){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    	return sf.format(time);
    }
    
    public List<String> comments(){
    	
    	List<CommentText> text = commentTextDao.selectCommentText();
		Random rand = new Random();
		List<String> comments=new ArrayList<String>();
		int num;
		for(int i=0;i<2;i++){
			num = rand.nextInt(text.size())+0;
			if("".equals(comments)){
				comments.add(text.get(num).getTextName());
				text.remove(num);////在集合中剔除已经有的对象
			}else{
				comments.add(text.get(num).getTextName());
			}
		}
		return comments;
    	
    }
    
}
