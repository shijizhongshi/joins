package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CommentText;
import com.ola.qh.service.ICommentTextService;
import com.ola.qh.util.Results;

/**
 * 
 * 
* @ClassName: CommentTextController
* @Description:  评论添加固定信息
* @author guozihan
* @date   2018/12/12
*
 */

@RestController
@RequestMapping(value="/api/commenttext")
public class CommentTextController {

	@Autowired
	private ICommentTextService commentTextService;
	
	
	@RequestMapping(value="/select")
	public Results<List<CommentText>> selectCommentText(){
		
		Results<List<CommentText>> results=new Results<List<CommentText>>();
		
		List<CommentText> list=commentTextService.selectCommentText();
		
		if(list==null || "".equals(list)){
			
			results.setStatus("1");
			return results;
			
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
