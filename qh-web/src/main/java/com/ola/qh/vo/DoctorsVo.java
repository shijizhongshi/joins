package com.ola.qh.vo;

import java.util.ArrayList;
import java.util.List;

import com.ola.qh.entity.DoctorReply;

public class DoctorsVo {

	private String doctorId;///医生的id;
	
    private String name;////医生的名称
	
	private String headImg;///头像
	
	private String hospital;//医院
	
	private String offices;///科室

	private String professional;////职称

	private String skilled;///擅长技能
	

	private List<DoctorReply> list=new ArrayList<DoctorReply>();


	public String getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHeadImg() {
		return headImg;
	}


	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}


	public String getHospital() {
		return hospital;
	}


	public void setHospital(String hospital) {
		this.hospital = hospital;
	}


	public String getOffices() {
		return offices;
	}


	public void setOffices(String offices) {
		this.offices = offices;
	}


	public String getProfessional() {
		return professional;
	}


	public void setProfessional(String professional) {
		this.professional = professional;
	}


	public String getSkilled() {
		return skilled;
	}


	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}


	public List<DoctorReply> getList() {
		return list;
	}


	public void setList(List<DoctorReply> list) {
		this.list = list;
	}
	
	
}
