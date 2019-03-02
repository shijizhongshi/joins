package com.ola.qh.controller;

import static org.mockito.Mockito.reset;

import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Business;
import com.ola.qh.service.IBannerService;
import com.ola.qh.service.IBusinessService;
import com.ola.qh.util.Results;

/**
 * 
 * 
 * @ClassName:BannerController
 * @Description:Banner图查询
 * @author guozihan
 * @date 2018年11月15日
 *
 */
@RestController
@RequestMapping("/api/banner")
public class BannerController {

	private static final Object Null = null;
	@Autowired
	private IBannerService bannerService;
	@Autowired
	private IBusinessService businessService;

	@RequestMapping(value = "/selectlist", method = RequestMethod.GET)
	public Results<List<Banner>> selectBanner(@RequestParam(name = "type", required = true) String type) {
		Results<List<Banner>> results = new Results<List<Banner>>();

		List<Banner> bannerlist = bannerService.selectBanner(type);
		if (bannerlist != null && bannerlist.size() != 0) {
			results.setData(bannerlist);
			results.setStatus("0");
			return results;
		}

		results.setMessage("图片为空");
		results.setStatus("1");
		return results;

	}

	/**
	 * 根据userID和address查询logo并返回
	 * 
	 * @param address 地址
	 * @param userid  用户ID
	 * @return
	 */
	@RequestMapping(value = "/selectLogo", method = RequestMethod.POST)
	public Results<List<Business>> selectLogo(@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "userid", required = true) String userid,
			@RequestParam(name = "type", required = true) String type) {
		Results<List<Business>> resultsBusiness = new Results<List<Business>>();
		Results<List<Banner>> resultsBanner = new Results<List<Banner>>();
		// 判断是否登录
		if (userid == null) {
			Integer count = businessService.selectByAddress(address);
			if (count == 0) {
				// 无匹配信息 使用默认logo
				List<Banner> list = bannerService.selectBanner(type);
				// 暂时无法返回默认logo····
				resultsBanner.setData(list);
				resultsBanner.setStatus("0");
				
				return resultsBanner;
				
			} else {
				// 有加盟商 使用加盟商logo
				List<Business> list = businessService.selectLogoByAddress(address);
				resultsBusiness.setData(list);
				resultsBusiness.setStatus("0");
				return resultsBusiness;
			}
		} else {
			// 登录状态 根据userID查询加盟商 先查询数量
			Integer count = businessService.selectCount(userid);
			if (count == 0) {
				// 没有加盟商 查询是否有固定的address
				// 根据userID查询address
				String addressString = businessService.selectAddressByUserId(userid);

				if (addressString.equals(Null)) {
					// address为空 使用默认logo
					// ??????????
				} else {
					// address存在 使用address查询加盟商logo
					List<Business> list = businessService.selectLogoByAddress(addressString);
					resultsBusiness.setStatus("0");
					resultsBusiness.setData(list);
					return resultsBusiness;
				}

				// 第一次查询 查询address
				// 第二次查询 查询logo

			} else {
				// 已登录 有加盟商 根据用户-加盟商关系表查logo
				List<Business> list = businessService.selectLogoByUserId(userid);
				resultsBusiness.setData(list);
				resultsBusiness.setStatus("0");
				return resultsBusiness;
			}
		}

		return null;
	}

}
