package com.ola.qh.entity;

import java.util.Date;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class User {

	private String id;
	
	private String nickname;//用户昵称
	
	@NotEmpty(message="密码不能为空")
	private String password;
	
	@NotEmpty(message="验证码不能为空")
	private String verification;
	
	private String address;
	
	private Date addtime;
	
	private String headimg;//用户头像
	
	private int userrole;//用户类型
	
	@NotEmpty(message="手机号不能为空")
	private String mobile;
	
	@NotEmpty(message = "设备名称不能为空")
	private String deviceName;
	
	@NotEmpty(message = "设备ID不能为空")
	private String deviceId;
	
	@NotEmpty(message = "设备编号不能为空")
	private String deviceToken;
	@Valid
	@NotNull
	private int deviceType;//设备类型
	
	private Date updatetime;
	
	private Date birthday;
	
	private int isdoctor;//1是医生   0:不是医生
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public int getUserrole() {
		return userrole;
	}

	public void setUserrole(int userrole) {
		this.userrole = userrole;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getIsdoctor() {
		return isdoctor;
	}

	public void setIsdoctor(int isdoctor) {
		this.isdoctor = isdoctor;
	}


	
}
