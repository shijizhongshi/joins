package com.ola.qh.dao;

import java.util.List;

import com.ola.qh.entity.DoctorInfo;

public interface DoctorOfficeDao {
	
	public List<DoctorInfo> officeList();

	public List<DoctorInfo> doctorInfoList();
}
