package com.ola.qh.controller;

import java.util.List;

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
	 * @param address 地址
	 * @param userid  用户ID
	 * @return
	 */
	@RequestMapping(value = "/selectLogo", method = RequestMethod.POST)
	public Results<Object> selectLogo(@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "userid", required = true) String userid,
			@RequestParam(name = "type", required = true) String type) {
		Results<Object> results = new Results<>();

		// 判断是否登录
		if (userid == null) {
			Integer count = businessService.selectByAddress(address);
			if (count == 0) {
				// 无匹配信息 使用默认logo
				List<Banner> list = bannerService.selectBanner(type);
				results.setData(list);
				results.setStatus("0");

				return results;

			} else {
				// 有加盟商 使用加盟商logo
				List<Business> list = businessService.selectLogoByAddress(address);
				results.setData(list);
				results.setStatus("0");
			
				return results;
			}
		} else {
			// 登录状态 根据userID查询加盟商 先查询数量
			Integer count = businessService.selectCount(userid);
			if (count == 0) {
				// 没有加盟商 查询是否有固定的address
				// 根据userID查询address
				String addressString = businessService.selectAddressByUserId(userid);

				if (addressString.equals(null)) {
					// 使用默认logo
					List<Banner> list = bannerService.selectBanner(type);
					results.setData(list);
					results.setStatus("0");

					return results;
				} else {
					// address存在 使用address查询加盟商logo
					List<Business> list = businessService.selectLogoByAddress(addressString);
					results.setStatus("0");
					results.setData(list);
					
					return results;
				}
			} else {
				// 已登录 有加盟商 根据用户-加盟商关系表查logo
				List<Business> list = businessService.selectLogoByUserId(userid);
				results.setData(list);
				results.setStatus("0");
				
				return results;
			}
		}
	}
}
