package com.ola.qh.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Address;
import com.ola.qh.util.Results;

public interface IAddressService {

	public List<Address> selectAddress(String userId,int pageNo,int pageSize);
	
	public Results<String> saveAddress(Address address);
	
	public Results<String> updateAddress(Address address);
	
	public int deleteAddress(@Param("id")String id);
	
	
}
