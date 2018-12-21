package com.ola.qh.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.DoctorOfficeDao;
import com.ola.qh.entity.DoctorInfo;
import com.ola.qh.service.IDoctorOfficeService;

@Service
public class DoctorOfficeService implements IDoctorOfficeService{

	@Autowired
	private DoctorOfficeDao doctorOfficeDao;
	@Override
	public List<DoctorInfo> officeList() {
		// TODO Auto-generated method stub
		return doctorOfficeDao.officeList();
	}

	/**
	 * 
	 */
	@Override
	public List<DoctorInfo> doctorInfoList() {
		// TODO Auto-generated method stub
		return doctorOfficeDao.doctorInfoList();
	}

}
