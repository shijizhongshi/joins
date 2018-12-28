package com.ola.qh.service;

import java.util.List;

import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorAndPatient;
import com.ola.qh.vo.DoctorVisitsVo;

public interface IDoctorVisitsService {

	public Results<DoctorVisitsVo> selectDoctorVisits(String offices,int issolve,String userId);
	
	public Results<List<DoctorAndPatient>> DoctorPatientList(String title);
}
