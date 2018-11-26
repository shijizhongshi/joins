package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Address;

public interface IAddressService {

	public List<Address> selectAddress(String userId,int pageNo,int pageSize);
	
	public int saveAddress(Address address);
	
	public int updateAddress(Address address);
	
	public int deleteAddress(@Param("id")String id);
}
