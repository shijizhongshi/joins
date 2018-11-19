package com.ola.qh.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Address;
import com.ola.qh.service.IAddressService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private IAddressService addressService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<Address>> selectAddress(@RequestParam(name = "userId", required = true) String userId) {

		Results<List<Address>> results = new Results<List<Address>>();

		List<Address> list = addressService.selectAddress(userId);

		results.setData(addressService.selectAddress(userId));
		results.setStatus("0");
		return results;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveAddress(@RequestBody @Valid Address address, BindingResult valid) {

		Results<String> results = new Results<String>();

		Pattern pattern = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
		pattern.matcher(address.getMobile()).matches();

		if (valid.hasErrors()) {
			results.setMessage("配送信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		if (!pattern.matcher(address.getMobile()).matches()) {
			results.setStatus("1");
			results.setMessage("手机号格式有误");
			return results;
		}
		address.setId(KeyGen.uuid());
		address.setAddtime(new Date());
		int save = addressService.saveAddress(address);

		if (save > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("添加失败");
		results.setStatus("1");
		return results;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Results<String> updateAddress(@RequestBody Address address) {

		Results<String> results = new Results<String>();

		Pattern pattern = Pattern.compile(Patterns.INTERNAL_MOBILE_PATTERN);
		pattern.matcher(address.getMobile()).matches();

		if (!pattern.matcher(address.getMobile()).matches()) {
			results.setStatus("1");
			results.setMessage("手机号格式有误");
			return results;
		}
		address.setUpdatetime(new Date());
		int update = addressService.updateAddress(address);

		if (update > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("修改失败");
		results.setStatus("1");
		return results;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteAddress(@RequestParam(name = "id", required = true) String id) {

		Results<String> results = new Results<String>();

		int delete = addressService.deleteAddress(id);
		if (delete > 0) {
			results.setStatus("0");
			return results;
		}
		results.setMessage("删除失败");
		results.setStatus("1");
		return results;

	}
}
