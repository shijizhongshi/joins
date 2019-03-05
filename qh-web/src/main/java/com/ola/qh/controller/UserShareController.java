package com.ola.qh.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.UserShare;
import com.ola.qh.service.IUserShareService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/usershare")
public class UserShareController {

	@Autowired
	private IUserShareService userShareService;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertUserShare(@RequestBody @Valid UserShare userShare,BindingResult valid){
		
		Results<String> results=new Results<String>();
		
		if(valid.hasErrors()){
			
			results.setMessage("信息不足");
			results.setStatus("1");
			return results;
		}
		
		userShare.setId(KeyGen.uuid());
		userShare.setAddtime(new Date());
		int save=userShareService.insertUserShare(userShare);
		
		if(save>0){
			results.setStatus("0");
			return results;
			
		}
		results.setStatus("1");
		return results;
	}
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<String> selectUserShare(@RequestParam(name="userId",required=true)String userId,@RequestParam(name="sectionId",required=true)String sectionId){
		
		Results<String> results=new Results<String>();
		
		UserShare userShare=userShareService.selectUserShare(userId, sectionId);
		if(userShare==null){
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteUserShare(@RequestParam(name="userId",required=true)String userId){
		
		Results<String> results=new Results<String>();
		
		int delete=userShareService.deleteUserShare(userId);
		if(delete>0){
			
			results.setStatus("0");
			return results;
			
		}
		results.setStatus("1");
		return results;
	}
}
