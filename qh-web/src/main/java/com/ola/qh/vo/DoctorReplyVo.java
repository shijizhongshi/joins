package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.DoctorPatient;

public class DoctorReplyVo extends DoctorPatient{

	private String patientTime;///患者提问时间
	
	private int roles;///1:游客   2:是患者本身  3:医生
	
	private List<DoctorsVo> list=new ArrayList<DoctorsVo>();

	
	public int getRoles() {
		return roles;
	}

	public void setRoles(int roles) {
		this.roles = roles;
	}

	public String getPatientTime() {
		return patientTime;
	}

	public void setPatientTime(String patientTime) {
		this.patientTime = patientTime;
	}

	public List<DoctorsVo> getList() {
		return list;
	}

	public void setList(List<DoctorsVo> list) {
		this.list = list;
	}

	
	
}
