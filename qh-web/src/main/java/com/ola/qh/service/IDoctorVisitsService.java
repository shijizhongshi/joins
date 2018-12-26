package com.ola.qh.service;

import com.ola.qh.util.Results;

public interface IDoctorVisitsService {

	public Results<String> selectDoctorVisits(String offices);
}
