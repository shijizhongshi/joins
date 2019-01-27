package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.ShopServeImg;
import com.ola.qh.service.IShopServeService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopServeDomain;
import com.ola.qh.vo.ShopServeVo;

@Service
public class ShopServeService implements IShopServeService {

	@Autowired
	private ShopServeDao shopServeDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private OrdersProductDao ordersProductDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> saveShopServe(ShopServe ss) {
		Results<String> result = new Results<String>();
		try {
			result = userService.existUser(ss.getUserId());
			if ("1".equals(result.getStatus())) {
				return result;
			}
			Shop shop = shopDao.singleShop(ss.getUserId(), ss.getShopId(), 0,null);
			/////// 必须是1服务店铺才有上传
			if (shop.getShopType() == 2) {
				result.setStatus("1");
				result.setMessage("店铺的类型不对");
				return result;
			}
			String sid = KeyGen.uuid();
			ss.setId(sid);
			ss.setAddtime(new Date());
			ss.setServeStatus(0);
			for (ShopServeImg ssi : ss.getImglist()) {
				ssi.setAddtime(new Date());
				ssi.setId(KeyGen.uuid());
				ssi.setImgUrl(ssi.getImgUrl());
				ssi.setServeId(sid);
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
	public Results<ShopServeVo> singleShopServe(String id) {
		// TODO Auto-generated method stub
		Results<ShopServeVo> result = new Results<ShopServeVo>();
		ShopServe ss = shopServeDao.selectSingle(id);
		List<ShopServeImg> listimg = shopServeDao.selectByServeId(id);
		ss.setImglist(listimg);
		String shopId = ss.getShopId();
		ShopServeVo vo=new ShopServeVo();
		BeanUtils.copyProperties(ss, vo);
		/////查出相应的店铺信息
		Shop shop = shopDao.singleShop(null, shopId, 1,null);
		double avgGrade=0;
		if(shopDao.commentGrade(shopId)!=null){
			avgGrade=shopDao.commentGrade(shopId);
		}
		vo.setAvgGrade(avgGrade);////平均分
		vo.setAddress(shop.getAddress());
		vo.setShopId(shopId);
		vo.setShopMobile(shop.getLeaderMobile());
		vo.setShopName(shop.getShopName());
		int buyCount = ordersProductDao.ordersCount(id);
		vo.setBuyCount(buyCount);///购买的数量
		
		ShopServeDomain ssd=new ShopServeDomain();
		ssd.setPageSize(0);
		ssd.setShopId(shopId);
		ssd.setId(id);
		ssd.setServeStatus(1);
		ssd.setPaymentType(ss.getPaymentType());///
		List<ShopServe> list = shopServeDao.selectList(ssd);
		for (ShopServe shopServe : list) {
			shopServe.setBuyCount(ordersProductDao.ordersCount(shopServe.getId()));
		}
		vo.setListserve(list);
		
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

	@Override
	public Results<List<ShopServe>> selectServeList(ShopServeDomain sdd) {
		// TODO Auto-generated method stub
		Results<List<ShopServe>> result = new Results<List<ShopServe>>();
		
		List<ShopServe> list = shopServeDao.selectList(sdd);
		for (ShopServe shopServe : list) {
			shopServe.setBuyCount(ordersProductDao.ordersCount(shopServe.getId()));
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	@Override
	public int selectListCount(String shopId, int serveStatus) {
		// TODO Auto-generated method stub
		return shopServeDao.selectListCount(shopId, serveStatus);
	}

}
