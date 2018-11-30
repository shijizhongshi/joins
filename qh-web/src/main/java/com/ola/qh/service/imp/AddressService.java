package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.AddressDao;
import com.ola.qh.entity.Address;
import com.ola.qh.service.IAddressService;

/**
 * 
 * 
* @ClassName: AddressService
* @Description:  用户地址信息的增删改查
* @author guozihan
* @date   2018年11月30日
*
 */
@Service
public class AddressService implements IAddressService{

	@Autowired 
	private AddressDao addressDao;
	
	@Override
	public List<Address> selectAddress(String userId,int pageNo,int pageSize){
		
		return addressDao.selectAddress(userId, pageNo, pageSize);
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
