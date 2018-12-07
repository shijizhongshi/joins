package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopServeType;
import com.ola.qh.service.IShopServeTypeService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/shopservetype")
public class ShopServeTypeController {
	@Autowired
	private IShopServeTypeService shopServeTypeService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<ShopServeType>> selectShopServeType() {

		Results<List<ShopServeType>> results = new Results<List<ShopServeType>>();

		List<ShopServeType> list=shopServeTypeService.selectShopServeType();
		if (list != null && list.size() != 0) {

			results.setData(list);
			results.setStatus("0");
			return results;

		}

		results.setStatus("1");
		return results;
	}

	
}

