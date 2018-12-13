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
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "page", required = true) int page) {

		return userCommentService.selectShopUserComment(shopId,userId, page);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Results<String> insertUserComment(@RequestBody @Valid UserComment usercomment, BindingResult valid) {

		Results<String> results = new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}

		return userCommentService.insertUserComment(usercomment);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserComment(@RequestParam(name = "id", required = false) String id
			,@RequestParam(name = "userId", required = false) String userId) {

		return userCommentService.deleteUserComment(id,userId);
	}

}
