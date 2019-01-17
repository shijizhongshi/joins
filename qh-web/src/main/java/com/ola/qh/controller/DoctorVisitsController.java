package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.DoctorAndPatients;
import com.ola.qh.service.IDoctorVisitsService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorVisitsVo;

@RestController
@RequestMapping(value="/api/doctorvisits")
public class DoctorVisitsController {

	@Autowired
	private IDoctorVisitsService doctorVisitsService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<DoctorVisitsVo> selectDoctorVisits(@RequestParam(name="offices",required=false)String offices,
			@RequestParam(name="issolve",required=true)int issolve,@RequestParam(name="userId",required=true)String userId){
		
		return doctorVisitsService.selectDoctorVisits(offices,issolve,userId);
	}
	
	@RequestMapping(value="/selectpatientlist",method=RequestMethod.GET)
	public Results<DoctorAndPatients> DoctorPatientList(@RequestParam(name="title",required=false)String title,
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="professional",required=false)String professional,
			@RequestParam(name="offices",required=false)String offices,
			@RequestParam(name="name",required=false)String name){
		
		
		return doctorVisitsService.DoctorPatientList(title, page, address, professional, offices, name);
	}
}
