package com.ola.qh.service;

import com.ola.qh.util.Results;
import com.ola.qh.vo.DoctorVisitsVo;

public interface IDoctorVisitsService {

	public Results<DoctorVisitsVo> selectDoctorVisits(String offices);
}
