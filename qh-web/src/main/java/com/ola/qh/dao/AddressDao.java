package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Address;

public interface AddressDao {

	public List<Address> selectAddress(String userId);
	
	public int saveAddress(Address address);
	
	public int updateAddress(Address address);
	
	public int deleteAddress(@Param("id")String id);
}
