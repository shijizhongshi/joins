package com.ola.qh.seivice.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.seivice.IBannerService;

@Service
public class BannerService implements IBannerService {

	@Autowired 
	private BannerDao bannerDao;

	@Override
	public List<Banner> selectBanner(String type) {
		
		return bannerDao.selectBanner(type);
	}
	
	
	

	
}
