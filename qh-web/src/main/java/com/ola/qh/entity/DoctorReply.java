package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class DoctorReply {

	private String id;
	
	@NotEmpty
	private String doctorId;
	@NotEmpty
	private String patientId;
	@NotEmpty
	private String replyContent;
	
	private Date addtime;


	private int readStatus;///1:已读
	
	
	private String showtime;
	
	

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
}
