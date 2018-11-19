package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.AddressDao;
import com.ola.qh.entity.Address;
import com.ola.qh.service.IAddressService;

@Service
public class AddressService implements IAddressService{

	@Autowired 
	private AddressDao addressDao;
	
	@Override
	public List<Address> selectAddress(String userId) {
		
		return addressDao.selectAddress(userId);
	}

	@Override
	public int saveAddress(Address address) {
		
		return addressDao.saveAddress(address);
	}

	@Override
	public int updateAddress(Address address) {
		
		return addressDao.updateAddress(address);
	}

	@Override
	public int deleteAddress(String id) {
		
		return addressDao.deleteAddress(id);
	}

	
}
