package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.vo.DoctorAndPatient;

public class DoctorAndPatients {

	private List<DoctorAndPatient> list=new ArrayList<DoctorAndPatient>();
	
	private List<Doctors> listdoctor=new ArrayList<Doctors>();

	public List<DoctorAndPatient> getList() {
		return list;
	}

	public void setList(List<DoctorAndPatient> list) {
		this.list = list;
	}

	public List<Doctors> getListdoctor() {
		return listdoctor;
	}

	public void setListdoctor(List<Doctors> listdoctor) {
		this.listdoctor = listdoctor;
	}
	
	
}
