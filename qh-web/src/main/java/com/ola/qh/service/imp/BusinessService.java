package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Business;
import com.ola.qh.service.IBusinessService;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private BusinessDao businessdao;
	@Autowired
	private BannerDao bannerDao;

	@Override
	public String selectLogo(String address, String userId) {

		// 判断是否登录
		if (userId == null) {
			Integer count = businessdao.selectCount(address);
			if (count == 0) {
				// 无匹配信息 使用默认logo
				String type = String.valueOf("2");
				List<Banner> list = bannerDao.selectBanner(type);
				String logoString = list.get(0).getImageurl();

				return logoString;

			} else {
				// 有加盟商 使用加盟商logo
				List<Business> list = businessdao.selectLogoByAddress(address);
				String logoString = list.get(0).getLogo();

				return logoString;
			}
		} else {
			// 登录状态 根据userID查询加盟商 先查询数量
			Integer count = businessdao.selectCountByUserId(userId);
			if (count == 0) {
				// 没有加盟商 查询是否有固定的address
				String addressString = businessdao.selectAddressByUserId(userId);

				if (addressString == null) {
					// 使用默认logo
					String type = String.valueOf("2");
					List<Banner> list = bannerDao.selectBanner(type);
					String logoString = list.get(0).getImageurl();

					return logoString;
				} else {
					// 根据userID查询businessID
					String businessId = businessdao.selectBusinessByUserId(userId);
					// 根据businessID查询logo
					String logoString = businessdao.selectByBusinessId(businessId);

					return logoString;
				}
			} else {
				// 已登录 有加盟商 根据用户-加盟商关系表用address查logo
				// 根据userID查询businessID
				String businessId = businessdao.selectBusinessByUserId(userId);
				// 根据businessID查询logo
				String logoString = businessdao.selectByBusinessId(businessId);

				return logoString;
			}
		}
	}
}
