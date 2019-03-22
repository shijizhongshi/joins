package com.ola.qh.controller;

import java.util.Arrays;
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

import com.ola.qh.entity.JobFair;
import com.ola.qh.entity.User;
import com.ola.qh.service.IJobFairService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/jobfair")
public class JobFairController {

	@Autowired
	private IJobFairService jobFairService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<List<JobFair>> selectJob(@RequestParam(name="id",required=false)String id,
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="category",required=false)String category,
			@RequestParam(name="salaryRangeMin",required=false)String salaryRangeMin,
			@RequestParam(name="salaryRangeMax",required=false)String salaryRangeMax,
			@RequestParam(name="welfare",required=false)String welfare,@RequestParam(name="page",required=true)int page){
		
				Results<List<JobFair>> results=new Results<List<JobFair>>();
				if(userId!=null && !"".equals(userId)){
					Results<User> userResult = userService.existUser(userId);
					if("1".equals(userResult.getStatus())){
						results.setStatus("1");
						results.setMessage(userResult.getMessage());
						return results;
					}
					userId=userResult.getData().getId();
				}
				int pageSize=Patterns.zupageSize;
				int pageNo=(page-1)*pageSize;
				
				
				List<JobFair> list=jobFairService.selectJob(id, userId, category, salaryRangeMin,salaryRangeMax, null, pageNo, pageSize);
					
				for (JobFair jobFair : list) {
					if(jobFair.getWelfare()!=null){
					String welfaress=jobFair.getWelfare();
					String[] welfares = welfaress.split(",");
					jobFair.setWelfares(Arrays.asList(welfares));
					}
					else{
						jobFair.setWelfares(null);
					}
				}
				
				results.setData(list);
				results.setStatus("0");
				return results;
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertJobFair(@RequestBody @Valid JobFair jobFair,BindingResult valid){
		
				Results<String> results=new Results<String>();
				if(valid.hasErrors()){
					
					results.setStatus("1");
					results.setMessage("信息填写不完整，请检查");
					return results;
					
				}
				Results<User> userResult = userService.existUser(jobFair.getUserId());
				if("1".equals(userResult.getStatus())){
					results.setStatus("1");
					results.setMessage(userResult.getMessage());
					return results;
				}
				jobFair.setUserId(userResult.getData().getId());
				jobFair.setId(KeyGen.uuid());
				jobFair.setCategory("招聘");
				jobFair.setAddtime(new Date());
				jobFairService.insertJobFair(jobFair);
				
				results.setStatus("0");
				return results;
		
	}
	@RequestMapping(value="/insertapply",method=RequestMethod.POST)
	public Results<String> insertJobApply(@RequestBody JobFair jobFair){
		
				Results<String> results=new Results<String>();
				if(jobFair.getUserId()==null || jobFair.getPosition()==null || jobFair.getMobile()==null || jobFair.getName()==null || jobFair.getExperienceDescribe()==null){
					
					results.setData("1");
					results.setMessage("信息填写不完整，请检查");
					return results;
					
				}
				jobFair.setId(KeyGen.uuid());
				jobFair.setCategory("求职");
				jobFair.setAddtime(new Date());
				jobFairService.insertJobApply(jobFair);
				
				results.setStatus("0");
				return results;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Results<String> updateJobFair(@RequestBody JobFair jobFair){
		
				Results<String> results=new Results<String>();
				
				jobFair.setUpdatetime(new Date());
				jobFairService.updateJobFair(jobFair);
				
				results.setStatus("0");
				return results;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteJobFair(@RequestParam(name="id",required=true)String id){
		
				Results<String> results=new Results<String>();
				
				jobFairService.deleteJobFair(id);
				
				results.setStatus("0");
				return results;
		
	}
}
