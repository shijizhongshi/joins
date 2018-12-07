package com.ola.qh.controller;

import java.util.Date;
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
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/userfavorite")
public class UserFavoriteController {

	@Autowired
	private IUserFavoriteService userFavoriteService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<UserFavorite>> selectUserFavorite(@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "productType", required = true) int productType){
		
		Results<List<UserFavorite>> results=new Results<List<UserFavorite>>();
		
		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		List<UserFavorite> list=userFavoriteService.selectUserFavorite(userId, pageNo, pageSize, productType);
		
		if (list != null && list.size() != 0) {
			results.setData(list);
			results.setStatus("0");
			return results;
		}

		results.setMessage("收藏为空");
		results.setStatus("1");
		return results;
		
		
		
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insertUserFavorite(@RequestBody @Valid UserFavorite userFavorite,BindingResult valid){
		
		
		Results<String> results=new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		userFavorite.setId(KeyGen.uuid());
		userFavorite.setAddtime(new Date());
		int insert=userFavoriteService.insertUserFavorite(userFavorite);
		
		if (insert<=0) {
			results.setStatus("1");
			return results;
		}

		
		results.setStatus("0");
		return results;
		
		
		
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteUserFavorite(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete=userFavoriteService.deleteUserFavorite(id);
		
		if (delete<=0) {
			results.setMessage("删除失败");
			results.setStatus("1");
			return results;
		}

		
		results.setStatus("0");
		return results;
		
		
		
	}
}
