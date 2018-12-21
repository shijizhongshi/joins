package com.ola.qh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.service.IDoctorOfficeService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/doctors")
public class DoctorOfficeController {

	@Autowired
	private IDoctorOfficeService doctorOfficeService;
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
		List<DoctorInfo> map= doctorOfficeService.officeList();
		
		result.setStatus("0");
		result.setData(map);
		return result;
	}
	
	@RequestMapping("/info/list")
	public Results<List<DoctorInfo>> listInfo(){
		Results<List<DoctorInfo>> result=new Results<List<DoctorInfo>>();
		List<DoctorInfo> map = doctorOfficeService.doctorInfoList();
		result.setData(map);
		result.setStatus("0");
		return result;
	}
}
