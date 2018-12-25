package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserComment;
import com.ola.qh.service.IUserCommentService;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName: UserCommentController
 * @Description: 用户评论的增删改查
 * @author guozihan
 * @date 2018/12/7
 *
 */
@RestController
@RequestMapping(value = "/api/usercomment")
public class UserCommentController {

	@Autowired
	private IUserCommentService userCommentService;

	@RequestMapping(value = "/selectshop", method = RequestMethod.GET)
	public Results<List<UserComment>> selectShopUserComment(
			@RequestParam(name = "shopId", required = false) String shopId,
			@RequestParam(name = "doctorId", required = false) String doctorId,
			@RequestParam(name = "page", required = true) int page) {

		return userCommentService.selectShopUserComment(shopId,doctorId, page);
	}

	@RequestMapping(value = "/insertshop", method = RequestMethod.POST)
	public Results<String> insertUserComment(@RequestBody @Valid UserComment usercomment, BindingResult valid) {

		Results<String> results = new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		if (usercomment.getShopId()==null) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}

		return userCommentService.insertUserComment(usercomment);
	}
	
	@RequestMapping(value = "/insertdoctor", method = RequestMethod.POST)
	public Results<String> insertDoctorComment(@RequestBody @Valid UserComment usercomment, BindingResult valid) {

		Results<String> results = new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}

		if (usercomment.getDoctorId()==null) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		return userCommentService.insertDoctorComment(usercomment);
	}

	@RequestMapping(value="/select", method = RequestMethod.GET)
	public Results<List<String>> selectCommentText(int textStatus){
		
		Results<List<String>> results=new Results<List<String>>();
		
		List<String> list=userCommentService.selectCommentText(textStatus);
		
		if(list==null || "".equals(list)){
			
			results.setStatus("1");
			return results;
			
		}
		
		results.setData(list);
		results.setStatus("0");
		return results;
	}
}
