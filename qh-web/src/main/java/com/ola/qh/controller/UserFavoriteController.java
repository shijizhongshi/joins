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

import com.ola.qh.entity.UserFavorite;
import com.ola.qh.service.IUserFavoriteService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
/**
 * 
 * 
* @ClassName: UserFavoriteController
* @Description:  我的收藏的增删查
* @author guozihan
* @date   
*
 */
@RestController
@RequestMapping(value = "/api/userfavorite")
public class UserFavoriteController {

	@Autowired
	private IUserFavoriteService userFavoriteService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserFavorite>> selectUserFavorite(
			@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "productType", required = true) int productType) {


		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		return userFavoriteService.selectUserFavorite(userId, pageNo, pageSize, productType);

		

	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertUserFavorite(@RequestBody @Valid UserFavorite userFavorite,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		return userFavoriteService.insertUserFavorite(userFavorite);

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserFavorite(
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "productId", required = false) String productId) {

		return userFavoriteService.deleteUserFavorite(id, userId, productId);


	}
	@RequestMapping(value = "/selectsearch", method = RequestMethod.GET)
	public Results<List<UserFavorite>> selectSearchUserFavorite(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "page", required = true) int page) {

		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		return userFavoriteService.selectSearchUserFavorite(userId, pageNo, pageSize, productName);

		

	}	
	
}
