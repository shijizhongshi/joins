package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IDoctorVisitsService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/doctorvisits")
public class DoctorVisitsController {

	@Autowired
	private IDoctorVisitsService doctorVisitsService;
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public Results<String> selectDoctorVisits(@RequestParam(name="offices",required=false)String offices){
		
		
		
		return doctorVisitsService.selectDoctorVisits(offices);
	}
}
