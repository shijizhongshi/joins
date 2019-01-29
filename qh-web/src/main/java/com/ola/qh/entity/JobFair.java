package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class JobFair {

	private String id;
	
	@NotEmpty(message="用户id不能为空")
	private String userId;
	
	@NotEmpty(message="发布单位不能为空")
	private String company;
	
	@NotEmpty(message="招聘的职位不能为空")
	private String position;
	
	private String skill;
	
	private String education;
	
	private String experience;
	
	private String salaryRange;
	
	private String welfare;
	
	@NotEmpty(message="联系电话不能为空")
	private String mobile;
	
	@NotEmpty(message="工作地址不能为空")
	private String address;
	
	@NotEmpty(message="职位信息不能为空")
	private String positionInfo;
	
	private Date addtime;
	
	private Date updatetime;
	
	private String name;
	
	private String experienceDescribe;
	
	private List<String> welfares=new ArrayList<String>();
	
	private String category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getWelfare() {
		return welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(String positionInfo) {
		this.positionInfo = positionInfo;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public List<String> getWelfares() {
		return welfares;
	}

	public void setWelfares(List<String> welfares) {
		this.welfares = welfares;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperienceDescribe() {
		return experienceDescribe;
	}

	public void setExperienceDescribe(String experienceDescribe) {
		this.experienceDescribe = experienceDescribe;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	
	
}
