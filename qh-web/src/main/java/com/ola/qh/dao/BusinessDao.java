package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.Business;

public interface BusinessDao {

	public Business single(@Param("id")String id,@Param("address")String address);
	
	public String singleBusinessUser(@Param("userId")String userId);
}
