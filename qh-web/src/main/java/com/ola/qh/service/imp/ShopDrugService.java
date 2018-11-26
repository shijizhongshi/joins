package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopDrugImgDao;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopDrugImg;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class ShopDrugService implements IShopDrugService{

	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private ShopDrugImgDao shopDrugImgDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> insertDrug(ShopDrug shopDrug) {
		Results<String> result = new Results<String>();
		try {
			String id=KeyGen.uuid();
			if(shopDrug.getImgList()!=null && shopDrug.getImgList().size()!=0){
				///////集合不等于空的话
				shopDrug.setImgUrl(shopDrug.getImgList().get(0).getImgUrl());
				for (ShopDrugImg sdi : shopDrug.getImgList()) {
					sdi.setId(KeyGen.uuid());
					sdi.setDrugId(id);
					sdi.setAddtime(new Date());
					shopDrugImgDao.insertDrugImg(sdi);
					//////保存药品图片的信息
				}
			}
			/////保存药品的信息
			shopDrug.setId(id);
			shopDrug.setAddtime(new Date());
			shopDrugDao.insertDrug(shopDrug);
			return result;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}
		
	}

	@Override
	public Results<String> updateDrug(ShopDrug shopDrug) {
		Results<String> result = new Results<String>();
		return result;
	}

	@Override
	public ShopDrug selectById(String drugId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopDrug> selectDrugList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
