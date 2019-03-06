package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Business;

public interface BusinessDao {

	public Business single(@Param("id")String id,@Param("address")String address);
	
	public String singleBusinessUser(@Param("userId")String userId);
	
	public List<Business> selectLogoByAddress(@Param("address")String address);
	
	public Integer selectCount(@Param("address")String address);
	
	public String selectAddressByUserId(@Param("userId")String userId);

	public List<Business> selectLogoByUserId(@Param("userId")String userId);
	
	public Integer selectCountByUserId(@Param("userId")String userId);
	
	public String selectBusinessByUserId(@Param("userId")String userId);
	
	public String selectByBusinessId(@Param("businessId") String businessId);
	
}
