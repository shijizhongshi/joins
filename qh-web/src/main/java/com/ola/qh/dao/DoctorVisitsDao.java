package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.vo.DoctorAndPatient;
import com.ola.qh.vo.DoctorVisitsVo;

public interface DoctorVisitsDao {

	public DoctorVisitsVo selectDoctorVisits(@Param("offices")String offices);
	
	public List<DoctorAndPatient> DoctorPatientList(@Param("title")String title);
}
