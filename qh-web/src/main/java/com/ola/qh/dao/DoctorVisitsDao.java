package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.vo.DoctorVisitsVo;

public interface DoctorVisitsDao {

	public DoctorVisitsVo selectDoctorVisits(@Param("offices")String offices);
}
