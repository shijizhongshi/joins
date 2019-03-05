package com.ola.qh.service.imp;

import static org.mockito.Matchers.anyList;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.AddressDao;
import com.ola.qh.dao.BannerDao;
import com.ola.qh.entity.Address;
import com.ola.qh.entity.Banner;
import com.ola.qh.service.IAddressService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.sun.org.apache.bcel.internal.generic.ISTORE;

/**
 * 
 * 
 * @ClassName: AddressService
 * @Description: 用户地址信息的增删改查
 * @author guozihan
 * @date 2018年11月30日
 *
 */
@Service
public class AddressService implements IAddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Address> selectAddress(String userId, int pageNo, int pageSize) {

		return addressDao.selectAddress(userId, pageNo, pageSize);
	}

	@Transactional
	@Override
	public Results<String> saveAddress(Address address) {

		Results<String> results = new Results<String>();

		try {
			Address address1 = addressDao.existAddress(address.getUserId());
			address.setId(KeyGen.uuid());
			address.setAddtime(new Date());

			if (address1 == null) {
				addressDao.saveAddress(address);

			} else {

				if ("1".equals(address.getIsdefault())) {
					address1.setUpdatetime(new Date());
					address1.setIsdefault("0");
					addressDao.updateAddress(address1);
				}

				addressDao.saveAddress(address);
			}
			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("添加失败");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<String> updateAddress(Address address) {

		Results<String> results = new Results<String>();

		try {

			Address address1 = addressDao.existAddress(address.getUserId());

			address.setUpdatetime(new Date());
			if (address1 == null) {

				addressDao.updateAddress(address);

			} else {
				/*if (address.getId().equals(address1.getId())) {

					addressDao.updateAddress(address);

				} else {*/
					if ("1".equals(address.getIsdefault()) && !address.getId().equals(address1.getId())) {

						address1.setUpdatetime(new Date());
						address1.setIsdefault("0");
						addressDao.updateAddress(address1);

					}
					addressDao.updateAddress(address);

				/*}*/
			}
			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("修改失败");
			return results;
		}
	}

	@Override
	public int deleteAddress(String id) {

		return addressDao.deleteAddress(id);
	}
}
