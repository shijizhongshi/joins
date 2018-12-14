package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.service.ISearchService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchProductVo;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopDrugDomain;
import com.ola.qh.vo.ShopServeDomain;
@Service
public class SearchService implements ISearchService{

	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private ShopServeDao shopServeDao;
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<List<SearchProductVo>> searchs(String searchName,String address,String status,int page) {
		// TODO Auto-generated method stub
		Results<List<SearchProductVo>> result=new Results<List<SearchProductVo>>();
		try {
			int pageSize=5;
			int pageNo=(page-1)*pageSize;
			List<SearchProductVo> searchList=new ArrayList<SearchProductVo>();
			
			if("0".equals(status)){
				////如果状态是0的话标识查商品
				ShopDrugDomain sdd = new ShopDrugDomain();
				sdd.setPageNo(pageNo);
				sdd.setPageSize(pageSize);
				sdd.setDrugName(searchName);
				List<ShopDrug> litDrug = shopDrugDao.selectDrugList(sdd);
				
				for (ShopDrug shopDrug : litDrug) {
					SearchProductVo vo=new SearchProductVo();
					BeanUtils.copyProperties(shopDrug, vo);
					vo.setProductId(shopDrug.getId());
					vo.setProductName(shopDrug.getDrugName());
					vo.setTypeSearch(1);////将来跳转到药品的详情页
					searchList.add(vo);
				}
				ShopServeDomain ssd=new ShopServeDomain();
				ssd.setPageNo(pageNo);
				ssd.setPageSize(pageSize);
				ssd.setServeName(searchName);
				List<ShopServe> listServe = shopServeDao.selectList(ssd);
				
				for (ShopServe shopServe : listServe) {
					SearchProductVo vo=new SearchProductVo();
					BeanUtils.copyProperties(shopServe, vo);
					vo.setProductId(shopServe.getId());
					vo.setProductName(shopServe.getServeName());
					vo.setTypeSearch(2);////将来跳转到服务项目的详情页
					searchList.add(vo);
				}
				result.setData(searchList);
				result.setStatus("0");
				return result;
			}
			if("1".equals(status)){
				/////通过店铺名查店铺
				ShopDomain sd = new ShopDomain();
				sd.setShopName(searchName);
				sd.setPageNo(pageNo);
				sd.setPageSize(pageSize);
				sd.setIsrecommend(1);
				List<Shop> shopList = shopDao.listShop(sd);
				for (Shop shop : shopList) {
					SearchProductVo vo=new SearchProductVo();
					vo.setAddress(shop.getAddress());
					vo.setImgUrl(shop.getDoorHeadUrl());
					vo.setProductId(shop.getId());
					vo.setProductName(shop.getShopName());
					vo.setGrade(0);
					vo.setTypeSearch(3);/////将来跳转店铺的详情页
					searchList.add(vo);
				}
				pageSize=2;
				pageNo=(page-1)*pageSize;
				//////查店铺
				ShopDrugDomain sdd =new ShopDrugDomain();
				sdd.setPageNo(pageNo);
				sdd.setPageSize(pageSize);
				sdd.setDrugName(searchName);
				List<ShopDrug> litDrug = shopDrugDao.selectDrugList(sdd);
				for (ShopDrug shopDrug : litDrug) {
					SearchProductVo vo=new SearchProductVo();
					vo.setProductId(shopDrug.getShopId());
					Shop shop = shopDao.singleShop(null, shopDrug.getShopId(), 0);
					vo.setAddress(shop.getAddress());
					////////还得加上评分
					vo.setGrade(0);
					vo.setProductName(shop.getShopName());
					vo.setImgUrl(shop.getDoorHeadUrl());
					vo.setTypeSearch(3);/////将来跳转店铺的详情页
					searchList.add(vo);
				}
				ShopServeDomain ssd=new ShopServeDomain();
				ssd.setPageNo(pageNo);
				ssd.setPageSize(pageSize);
				ssd.setServeName(searchName);
				List<ShopServe> listServe = shopServeDao.selectList(ssd);
				for (ShopServe shopServe : listServe) {
					SearchProductVo vo=new SearchProductVo();
					vo.setProductId(shopServe.getShopId());
					List<Shop> shop = shopDao.selectShopByUserId(null, shopServe.getShopId(), 0);
					vo.setAddress(shop.get(0).getAddress());
					////////还得加上评分
					vo.setGrade(0);
					vo.setProductName(shop.get(0).getShopName());
					vo.setImgUrl(shop.get(0).getDoorHeadUrl());
					vo.setTypeSearch(3);/////将来跳转店铺的详情页
					searchList.add(vo);
				}
			}
			
			result.setData(searchList);
			result.setStatus("0");
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("查询失败");
			return result;
		}
		
		
	}

}
