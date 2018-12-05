package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;
import com.ola.qh.service.IShopServeService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class ShopServeService implements IShopServeService {

	@Autowired
	private ShopServeDao shopServeDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> saveShopServe(ShopServe ss) {
		Results<String> result = new Results<String>();
		try {
			result = userService.existUser(ss.getUserId());
			if ("1".equals(result.getStatus())) {
				return result;
			}
			List<Shop> shoplist = shopDao.selectShopByUserId(ss.getUserId(), ss.getShopId(), 0);
			/////// 必须是1服务店铺才有上传
			if (shoplist.get(0).getShopType() == 2) {
				result.setStatus("1");
				result.setMessage("店铺的类型不对");
				return result;
			}
			String id = KeyGen.uuid();
			ss.setId(id);
			ss.setAddtime(new Date());
			ss.setServeStatus(0);
			for (ShopServeImg ssi : ss.getImglist()) {
				ssi.setAddtime(new Date());
				ssi.setId(KeyGen.uuid());
				ssi.setImgUrl(ssi.getImgUrl());
				ssi.setServeId(id);
				shopServeDao.insertServeImg(ssi);
			}
			shopServeDao.insertServe(ss);
			result.setStatus("0");
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> updateShopServe(ShopServe ss) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		try {
			ss.setUpdatetime(new Date());
			shopServeDao.updateServe(ss);
			if (ss.getImglist() != null && ss.getImglist().size() > 0) {
				////// 原本数据库的参数
				List<ShopServeImg> originalList = shopServeDao.selectByServeId(ss.getId());
				List<ShopServeImg> ssiList = ss.getImglist();///// 传过来的参数
				for (int i = 0; i < originalList.size(); i++) {
					int z = 0;
					for (; z < ssiList.size(); z++) {
						if (originalList.get(i).getId().equals(ssiList.get(z).getId())) {
							///// 说明这个图片的id没有被删除
							break;
						}
					}
					if (z == ssiList.size()) {
						////// 说明这个id被删除掉了
						shopServeDao.deleteServeImg(originalList.get(i).getId());
					}
				}
				for (ShopServeImg shopServeImg : ssiList) {
					if (shopServeImg.getId() == null || "".equals(shopServeImg.getId())) {
						shopServeImg.setAddtime(new Date());
						shopServeImg.setServeId(ss.getId());
						shopServeImg.setId(KeyGen.uuid());
						shopServeDao.insertServeImg(shopServeImg);
					}
				}

			}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
	}

	@Override
	public Results<ShopServe> singleShopServe(String id) {
		// TODO Auto-generated method stub
		Results<ShopServe> result = new Results<ShopServe>();
		ShopServe ss = shopServeDao.selectSingle(id);
		List<ShopServeImg> listimg = shopServeDao.selectByServeId(id);
		ss.setImglist(listimg);
		result.setStatus("0");
		result.setData(ss);
		return result;
	}

	@Override
	public Results<List<ShopServe>> selectServeList(String shopId, String id, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Results<List<ShopServe>> result = new Results<List<ShopServe>>();
		List<ShopServe> list = shopServeDao.selectList(shopId, id, pageNo, pageSize,null);
		for (ShopServe shopServe : list) {
			List<ShopServeImg> imgList = shopServeDao.selectByServeId(shopServe.getId());
			shopServe.setImglist(imgList);
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}

}
