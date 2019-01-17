package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.Doctors;

public class DoctorAndPatient {

	private List<Doctors> list=new ArrayList<Doctors>();
	
	private String patientId;
	
	private String title;
	
	private String addtime;
	
	private String office;
	
	private String readStatus;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<Doctors> getList() {
		return list;
	}

	public void setList(List<Doctors> list) {
		this.list = list;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	
	
	
	
}
