package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IDoctorVisitsService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorAndPatient;
import com.ola.qh.vo.DoctorVisitsVo;

@RestController
@RequestMapping(value="/api/doctorvisits")
public class DoctorVisitsController {

	@Autowired
	private IDoctorVisitsService doctorVisitsService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<DoctorVisitsVo> selectDoctorVisits(@RequestParam(name="offices",required=false)String offices,
			@RequestParam(name="issolve",required=true)int issolve){
		
		return doctorVisitsService.selectDoctorVisits(offices,issolve);
	}
	
	@RequestMapping(value="/selectpatientlist",method=RequestMethod.GET)
	public Results<List<DoctorAndPatient>> DoctorPatientList(@RequestParam(name="title",required=false)String title){
		
		
		return doctorVisitsService.DoctorPatientList(title);
	}
}
