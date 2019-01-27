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

import com.ola.qh.entity.UserDouDou;
import com.ola.qh.service.IUserDoudouService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/doudou")
public class UserDoudouController {

	@Autowired
	private IUserDoudouService userDoudouService;
	/**
	 * 看视频的时候得豆豆
	 * @param udd
	 * @param valid
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> insert(@RequestBody @Valid UserDouDou udd,BindingResult valid){
		
		 Results<String> result=new  Results<String>();
		 if(valid.hasErrors()){
			 result.setStatus("1");
			 result.setMessage("信息填写不完整");
			 return result;
		 }
		 userDoudouService.insertDoudou(udd);
		 result.setStatus("0");
		 return result;
	} 
	
	/**
	 * 查询豆豆的明细
	 * @param page
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<UserDouDou>> list(@RequestParam(name="page",required=true)int page,
			@RequestParam(name="userId",required=true)String userId){
		
		Results<List<UserDouDou>> result=new Results<List<UserDouDou>>();
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		List<UserDouDou> list = userDoudouService.listDoudou(userId, pageNo, pageSize);
		for (UserDouDou userDouDou : list) {
			userDouDou.setShowtime(Patterns.sfDetailTime(userDouDou.getAddtime()));
		}
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
}
