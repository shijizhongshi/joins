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

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.entity.Reply;
import com.ola.qh.service.IDoctorReplyService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorReplyVo;

@RestController
@RequestMapping("/api/doctors")
public class DoctorReplyController {

	@Autowired
	private IDoctorReplyService doctorReplyService;
	/**
	 * 科室
	 * <p>
	 * Title: listoffice
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/office/list")
	public Results<List<DoctorInfo>> listoffice() {

		Results<List<DoctorInfo>> result = new Results<List<DoctorInfo>>();
		List<DoctorInfo> map= doctorReplyService.officeList();
		
		result.setStatus("0");
		result.setData(map);
		return result;
	}
	
	@RequestMapping("/info/list")
	public Results<List<DoctorInfo>> listInfo(){
		Results<List<DoctorInfo>> result=new Results<List<DoctorInfo>>();
		List<DoctorInfo> map = doctorReplyService.doctorInfoList();
		result.setData(map);
		result.setStatus("0");
		return result;
	}
	
	/**
	 * 问答的详情页
	 * <p>Title: singleReplyPatient</p>  
	 * <p>Description: </p>  
	 * @param patientId
	 * @return
	 */
	/*@RequestMapping("/reply/single")
	public Results<DoctorReplyVo> singleReplyPatient(
			@RequestParam(name="patientId",required=true)String patientId,
			@RequestParam(name="userId",required=false)String userId){
		return doctorReplyService.singleReply(patientId,userId);////这个表示的是问答的详情(所有问答)
	}*/
	
	@RequestMapping(value="/save/reply",method=RequestMethod.POST)
	public Results<String> insertReply(@RequestBody @Valid Reply dr,BindingResult valid){
		
		Results<String> result=new Results<String>();
		if(valid.hasErrors()){
			result.setStatus("1");
			result.setMessage("回复信息不完整");
			return result;
		}
		return doctorReplyService.insertReply(dr);
		
	}
	
	@RequestMapping(value="/reply/list",method=RequestMethod.GET)
	public Results<List<Reply>> listReply(@RequestParam(name="page",required=true)int page,
			@RequestParam(name="patientId",required=true)String patientId,
			@RequestParam(name="userId",required=false)String userId){
		
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		return doctorReplyService.listReply(patientId, pageNo, pageSize, userId);
	
		
	}
	/**
	 * 
	 * @param id
	 * @param likes 1:增加   其他:减少
	 * @return
	 */
	@RequestMapping(value="/reply/update",method=RequestMethod.GET)
	public Results<String> updateReply(
			@RequestParam(name="id",required=true)String id,
			@RequestParam(name="userId",required=true)String userId){
		return doctorReplyService.updateReply(id, userId);
		
	}	
	
}
