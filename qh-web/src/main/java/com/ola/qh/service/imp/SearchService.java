package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.JobFairDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.entity.DoctorPatient;
import com.ola.qh.entity.JobFair;
import com.ola.qh.entity.News;
import com.ola.qh.entity.SearchCircleVo;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.service.ISearchService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchProductVo;
import com.ola.qh.vo.SearchVo;
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
	private NewsDao newsDao;
	@Autowired
	private DoctorsDao doctorsDao;
	@Autowired
	private JobFairDao jobFairDao;
	
	
	
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<SearchVo> searchs(String searchName,String address,String status,int page) {
		// TODO Auto-generated method stub
		Results<SearchVo> result=new Results<SearchVo>();
		try {
			int pageSize=5;
			int pageNo=(page-1)*pageSize;
			SearchVo searchVo=new SearchVo();
			List<SearchProductVo> searchList=new ArrayList<SearchProductVo>();
			
			if("0".equals(status)){
				List<SearchProductVo> shoplist=new ArrayList<SearchProductVo>();
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
					Shop shop = shopDao.singleShop(null, shopDrug.getShopId(), 0,address);
					if(shop!=null){
						SearchProductVo vo1=new SearchProductVo();
						vo1.setAddress(shop.getAddress());
						vo1.setImgUrl(shop.getDoorHeadUrl());
						vo1.setProductId(shop.getId());
						vo1.setProductName(shop.getShopName());
						vo1.setGrade(0);
						vo1.setTypeSearch(3);/////将来跳转店铺的详情页
						vo1.setShopType("2");
						shoplist.add(vo1);
					}
					
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
					Shop shop = shopDao.singleShop(null, shopServe.getShopId(), 0,address);
					if(shop!=null){
						SearchProductVo vo1=new SearchProductVo();
						vo1.setAddress(shop.getAddress());
						vo1.setImgUrl(shop.getDoorHeadUrl());
						vo1.setProductId(shop.getId());
						vo1.setProductName(shop.getShopName());
						vo1.setGrade(0);
						vo1.setTypeSearch(3);/////将来跳转店铺的详情页
						vo1.setShopType("1");
						shoplist.add(vo1);
					}
					searchList.add(vo);
					
				}
				
				ShopDomain sd = new ShopDomain();
				sd.setShopName(searchName);
				sd.setPageNo(pageNo);
				sd.setPageSize(pageSize);
				sd.setAddress(address);
				List<Shop> shopList = shopDao.listShop(sd);
				for (Shop shop : shopList) {
					SearchProductVo vo1=new SearchProductVo();
					vo1.setAddress(shop.getAddress());
					vo1.setImgUrl(shop.getDoorHeadUrl());
					vo1.setProductId(shop.getId());
					vo1.setProductName(shop.getShopName());
					vo1.setGrade(0);
					vo1.setTypeSearch(3);/////将来跳转店铺的详情页
					vo1.setShopType(String.valueOf(shop.getShopType()));
					shoplist.add(vo1);
				}
				searchVo.setProductVo(searchList);
				searchVo.setShopVo(shoplist);
				result.setData(searchVo);
				result.setStatus("0");
				return result;
			}
			if("1".equals(status)){
				/////通过店铺名查店铺
				/////status==2的时候是商城店铺   status==3的时候是服务店铺
				ShopDomain sd = new ShopDomain();
				sd.setShopName(searchName);
				sd.setPageNo(pageNo);
				sd.setPageSize(pageSize);
				sd.setAddress(address);
				if("2".equals(status)){
					sd.setShopType(2);
					
				}else if("3".equals(status)){
					sd.setShopType(1);
					
				}
				List<Shop> shopList = shopDao.listShop(sd);
				for (Shop shop : shopList) {
					SearchProductVo vo=new SearchProductVo();
					vo.setAddress(shop.getAddress());
					vo.setImgUrl(shop.getDoorHeadUrl());
					vo.setProductId(shop.getId());
					vo.setProductName(shop.getShopName());
					vo.setGrade(0);
					vo.setTypeSearch(3);/////将来跳转店铺的详情页
					vo.setShopType(String.valueOf(shop.getShopType()));
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
					Shop shop = shopDao.singleShop(null, shopDrug.getShopId(), 0,null);
					if(shop!=null){
						vo.setAddress(shop.getAddress());
						////////还得加上评分
						vo.setGrade(0);
						vo.setProductName(shop.getShopName());
						vo.setImgUrl(shop.getDoorHeadUrl());
						vo.setTypeSearch(3);/////将来跳转店铺的详情页
						vo.setShopType(String.valueOf(shop.getShopType()));
						searchList.add(vo);
					}
					
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
					if(shop!=null){
						vo.setAddress(shop.get(0).getAddress());
						////////还得加上评分
						vo.setGrade(0);
						vo.setProductName(shop.get(0).getShopName());
						vo.setImgUrl(shop.get(0).getDoorHeadUrl());
						vo.setTypeSearch(3);/////将来跳转店铺的详情页
						vo.setShopType(String.valueOf(shop.get(0).getShopType()));
						searchList.add(vo);
					}
					
				}
			}
			/////展示店铺信息
			searchVo.setProductVo(searchList);
			result.setData(searchVo);
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

	@Override
	public Results<SearchCircleVo> searchCircle(String searchName, String types, int page) {
		// TODO Auto-generated method stub
		Results<SearchCircleVo> result=new Results<SearchCircleVo>();
		SearchCircleVo vo=new SearchCircleVo();
		List<News> newsList=new ArrayList<News>(); 
		
		List<JobFair> jobList=new ArrayList<JobFair>(); 
		
		List<DoctorPatient> paList=new ArrayList<DoctorPatient>();
		int pageNo=(page-1)*Patterns.zupageSize;
		int pageSize=Patterns.zupageSize;
		
		if("1".equals(types)){
			/////资讯的搜索
			newsList=newsDao.selectNewList(pageNo, pageSize,null, null, searchName);
			
		}else if("2".equals(types)){
			/////职位的搜索
			jobList=jobFairDao.selectJob(null, null, null, null, null, searchName, pageNo, pageSize);
		}else{
			//////医学圈全部搜索
			newsList=newsDao.selectNewList(0, 3,null, null, searchName);
			jobList=jobFairDao.selectJob(null, null, null, null, null, searchName, 0, 3);
			///////动态
			paList=doctorsDao.listPatient(null, null, searchName, pageNo, pageSize);
		}
		vo.setNewsList(newsList);
		vo.setJobList(jobList);
		vo.setPaList(paList);
		
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

}
