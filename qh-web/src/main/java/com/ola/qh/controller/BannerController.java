package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Banner;
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
	 * 
	 * @param address 地址
	 * @param userId  用户ID
	 * @return
	 */
	@RequestMapping(value = "/selectLogo", method = RequestMethod.GET)
	public Results<String> selectLogo(@RequestParam(name = "address", required = true) String address,
			@RequestParam(name = "userId", required = false) String userId) {
		Results<String> results = new Results<String>();

		String logoString = businessService.selectLogo(address, userId);
		results.setStatus("0");
		results.setData(logoString);

		return results;
	}
}
