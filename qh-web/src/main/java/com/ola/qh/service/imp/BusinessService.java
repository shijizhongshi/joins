package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.entity.Business;
import com.ola.qh.service.IBusinessService;

@Service
public class BusinessService implements IBusinessService{
	
	@Autowired
	private BusinessDao businesssdao;
	@Autowired
	private BannerDao bannerDao;

	@Override
	public List<Business> selectLogo(String userid, String address) {
		
		return null;
	}

	@Override
	public Integer selectByAddress(String address) {
		//根据address查询加盟商logo
		Integer count = businesssdao.selectByAddress(address);
		return count;
	
	}

	@Override
	public List<Business> selectLogoByAddress(String address) {
		//根据address查询business表中的加盟商logo
		List<Business> list = businesssdao.selectLogoByAddress(address);
		return list;
	}

	@Override
	public Integer selectCount(String userid) {
		//根据userID查询数量
		
		return businesssdao.selectCount(userid);
	}

	@Override
	public String selectAddressByUserId(String userid) {
		//根据userID查询address
		String addreString = businesssdao.selectAddressByUserId(userid);
		
		return addreString;
	}

	@Override
	public List<Business> selectLogoByUserId(String userid) {
		List<Business> list = businesssdao.selectLogoByUserId(userid);
		
		return list;
	}
		
	
}
