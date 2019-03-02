package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.Business;

public interface IBusinessService {
	
	public List<Business> selectLogo(String userid,String address);
	
	public Integer selectByAddress(String address);
	
	public List<Business> selectLogoByAddress(String address);
	
	public Integer selectCount(String userid);
	
	public String selectAddressByUserId(String userid);
	
	public List<Business> selectLogoByUserId(String userid);
}
