package com.ola.qh.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.ShopDrugCart;
import com.ola.qh.service.IShopDrugCartService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CartVo;

/**
 * 
 * 
 * @ClassName: ShopDrugCartController
 * @Description: 购物车的增删改查
 * @author guozihan
 * @date 2018/12/7
 *
 */
@RestController
@RequestMapping(value = "/api/shopdrugcart")
public class ShopDrugCartController {

	@Autowired
	private IShopDrugCartService shopDrugCartService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<CartVo>> selectShopDrugCart(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "page", required = true) int page) {

		Results<List<CartVo>> results = new Results<List<CartVo>>();

		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		List<CartVo> list = shopDrugCartService.selectShopDrugCart(userId, pageNo, pageSize);
		results.setData(list);
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> insertShopDrugCart(@RequestBody @Valid ShopDrugCart shopDrugCart, BindingResult valid) {

		Results<String> results = new Results<String>();

		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		return shopDrugCartService.insertShopDrugCart(shopDrugCart);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Results<String> updateShopDrugCart(@RequestParam(name = "count", required = true) int count,
			@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		Date updatetime = new Date();

		int update = shopDrugCartService.updateShopDrugCart(count, id, updatetime);

		if (update <= 0) {
			results.setMessage("修改出错");
			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteShopDrugCart(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete = shopDrugCartService.deleteShopDrugCart(id);

		if (delete <= 0) {
			results.setMessage("删除出现异常");
			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.GET)
	public Results<String> deleteAllShopDrugCart(@RequestParam(name = "userId", required = true) String userId) {

		Results<String> results = new Results<String>();

		int deleteall = shopDrugCartService.deleteAllShopDrugCart(userId);

		if (deleteall <= 0) {
			results.setMessage("删除出现异常");
			results.setStatus("1");
			return results;

		}
		results.setStatus("0");
		return results;
	}

}
