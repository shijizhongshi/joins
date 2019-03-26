package com.ola.qh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.dao.BannerDao;
import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.NewsDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopDrugImgDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.dao.UserCommentDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.Banner;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.News;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopDrugImg;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.User;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ClassVo;
import com.ola.qh.vo.CourseClassDomain;
import com.ola.qh.vo.DrugIndexVo;
import com.ola.qh.vo.IndexVo;
import com.ola.qh.vo.ServerIndexVo;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopDrugDomain;
import com.ola.qh.vo.ShopServeDomain;

@RestController
@RequestMapping("/api/index")
public class IndexController {

	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private ShopServeDao shopServeDao;
	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ShopDrugImgDao shopDrugImgDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BusinessDao businessDao;
	
	
	@RequestMapping("/select")
	public Results<IndexVo> selectList() {
		Results<IndexVo> result = new Results<IndexVo>();
		IndexVo vo = new IndexVo();
		List<Banner> bannerlist = bannerDao.selectBanner("1");
		vo.setBannerlist(bannerlist);
		ShopDomain shopDoamin = new ShopDomain();
		shopDoamin.setPageNo(0);
		shopDoamin.setPageSize(6);
		shopDoamin.setIsrecommend(1);
		
		List<Shop> shoplist = shopDao.listShop(shopDoamin);
		vo.setShoplist(shoplist);
		ShopDrugDomain sdd = new ShopDrugDomain();
		sdd.setIshot(1);
		sdd.setStatus(0);
		sdd.setPageNo(0);
		sdd.setPageSize(6);
		List<ShopDrug> druglist = shopDrugDao.selectDrugList(sdd);
		vo.setDruglist(druglist);
		CourseClassDomain ccd=new CourseClassDomain();
		ccd.setIsremmend(1);
		ccd.setPageNo(0);
		ccd.setPageSize(6);
		vo.setClasslist(courseClassDao.classList(ccd));
		List<News> newlist = newsDao.selectNewList(0, 6, "1", null, null);
		for (News news : newlist) {
			/// 封装这个对象的时间参数
			if (news.getAddtime() != null) {
				String times = getTime(news.getAddtime(),new Date());
				news.setShowtime(times);
			}

		}
		vo.setNewlist(newlist);
		result.setStatus("0");
		result.setData(vo);
		return result;
	}


	@RequestMapping("/drug")
	public Results<DrugIndexVo> drugIndexs(){
		
		Results<DrugIndexVo> result=new Results<DrugIndexVo>();
		DrugIndexVo vo=new DrugIndexVo();
		List<Banner> bannerlist = bannerDao.selectBanner("3");
		vo.setBannerList(bannerlist);
		List<Banner> hotlist = bannerDao.selectBanner("4");
		vo.setHotlist(hotlist);////品牌热卖的(两个固定的图片)
		List<Banner> urllist = bannerDao.selectBanner("5");
		if(urllist!=null && urllist.size()!=0){
			vo.setBannerUrl(urllist.get(0).getImageurl());///一张图片
		}
		
		/////限时特惠
		ShopDrugDomain sdd = new ShopDrugDomain();
		sdd.setStatus(0);
		sdd.setPageNo(0);
		sdd.setPageSize(8);
		sdd.setIstimes(1);///审批过的限时特惠
		List<ShopDrug> timelist = shopDrugDao.selectDrugList(sdd);
		vo.setTimeList(timelist);
		//////推荐店铺
		ShopDomain shopDoamin = new ShopDomain();
		shopDoamin.setPageNo(0);
		shopDoamin.setPageSize(6);
		shopDoamin.setShopType(2);///商城店铺
		shopDoamin.setIsrecommend(1);////推荐店铺
		List<Shop> shoplist = shopDao.listShop(shopDoamin);
		for(Shop shop : shoplist) {
			List<ShopImg> shopImgList=new ArrayList<ShopImg>();
			
			List<ShopDrug> sdlist = shopDrugDao.shopDrugByShopId(shop.getId());
			for (ShopDrug shopDrug : sdlist) {
				ShopImg img=new ShopImg();
				List<ShopDrugImg> simg = shopDrugImgDao.listDrugImg(shopDrug.getId());
				img.setImgUrl(simg.get(0).getImgUrl());
				shopImgList.add(img);
			}
			shop.setImgList(shopImgList);
		}
		vo.setShoplist(shoplist);
		
		////热卖商品列表
		ShopDrugDomain drugDoamin = new ShopDrugDomain();
		drugDoamin.setPageNo(0);
		drugDoamin.setPageSize(6);
		drugDoamin.setIsrecommend(1);////推荐店铺
		List<ShopDrug> druglist = shopDrugDao.selectDrugList(sdd);
		vo.setDruglist(druglist);
		result.setData(vo);
		result.setStatus("0");
		return result;
	}
	
	/**
	 * 中医院的首页
	 * <p>Title: serveIndex</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	@RequestMapping("/serve")
	public Results<ServerIndexVo> serveIndex(
			@RequestParam(name="address",required=true)String address){
		Results<ServerIndexVo> result=new Results<ServerIndexVo>();
		ServerIndexVo vo = new ServerIndexVo();
		List<Banner> bannerlist = bannerDao.selectBanner("2");
		vo.setBannerlist(bannerlist);
		
		ShopDomain sd=new ShopDomain();
		sd.setAddress(address);
		sd.setShopType(1);
		sd.setPageNo(0);
		sd.setPageSize(6);
		List<Shop> nearbylist = shopDao.listShop(sd);
		for (Shop shop : nearbylist) {
			Patterns p=new Patterns();
			shop.setComments(comments(0));
		}
		vo.setNearbylist(nearbylist);
		
		ShopServeDomain ssd=new ShopServeDomain();
		ssd.setServeStatus(1);///后台审核过的服务项目
		ssd.setIshot(1);
		ssd.setPageNo(0);
		ssd.setPageSize(6);
		List<ShopServe> sslist = shopServeDao.selectList(ssd);
		vo.setServelist(sslist);
		
		ShopDomain sd1=new ShopDomain();
		sd1.setShopType(1);
		sd1.setPageNo(0);
		sd1.setPageSize(6);
		List<Shop> shoplist = shopDao.listShop(sd1);
		vo.setShopList(shoplist);////猜你喜欢服务店铺的集合

		result.setStatus("0");
		result.setData(vo);
		return result;
	}
	
	@RequestMapping("/class")
	public Results<ClassVo> courseClass(
			@RequestParam(name="typeName",required=false)String typeName,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="userId",required=false)String userId){
		Results<ClassVo> result=new Results<ClassVo>();
		ClassVo vo =new ClassVo();
		String newAddress=null;
		List<Banner> bannerlist=new ArrayList<Banner>();
		Banner banner=new Banner();
		if(userId!=null && !"".equals(userId)){
			String businessId = businessDao.singleBusinessUser(userId);
			if(businessId!=null){
				//////说明这个用户有固定的加盟商
				Business b = businessDao.single(businessId,null);
				banner.setImageurl(b.getBanner());
				bannerlist.add(banner);
			}else{
				User user = userDao.singleUser(userId, null);
				if(user.getAddress()!=null && user.getAddress()!=""){
					newAddress=user.getAddress();
				}
			}
			
		}else if(newAddress==null && bannerlist.size()==0){
			if(address!=null && !"".equals(address)){
				newAddress=address;
			}
			
		}
		if(newAddress!=null && !"".equals(newAddress)){
		
			Business b = businessDao.single(null,newAddress);
			if(b!=null){
				////////查加盟商(加盟商存在的话将其的banner赋值)
				banner.setImageurl(b.getBanner());
				bannerlist.add(banner);
			}else{
			////没有查到这个地址的加盟商的时候就是系统的banner
				bannerlist=bannerDao.selectBanner("1");
				vo.setBannerlist(bannerlist);
			}
		}else{
			bannerlist=bannerDao.selectBanner("1");
			vo.setBannerlist(bannerlist);
		}
		
		
		if(typeName==null || "".equals(typeName)){
			CourseClassDomain ccd=new CourseClassDomain();
			ccd.setIsremmend(1);
			ccd.setPageNo(0);
			ccd.setPageSize(4);
			/////免费课程的集合
			vo.setNofreelist(courseClassDao.nofreeList(ccd));
			///推荐课程的集合
			vo.setCourseClass(courseClassDao.classList(ccd));
			////直播的集合
			CourseClassDomain ccdlive=new CourseClassDomain();
			ccdlive.setIsremmend(1);
			ccdlive.setPageNo(0);
			ccdlive.setPageSize(4);
			List<CourseLineShow> livelist = courseDao.selectLiveList(ccdlive);
			vo.setLivelist(livelist);
		}else{
			CourseClassDomain ccd=new CourseClassDomain();
			ccd.setCourseTypeSubclassName(typeName);
			ccd.setPageNo(0);
			ccd.setPageSize(4);
			/////免费课程的集合
			vo.setNofreelist(courseClassDao.nofreeList(ccd));
			///推荐课程的集合
			vo.setCourseClass(courseClassDao.classList(ccd));
			////直播的集合
			CourseClassDomain ccdlive=new CourseClassDomain();
			ccdlive.setIsremmend(1);
			ccdlive.setPageNo(0);
			ccdlive.setPageSize(4);
			/*ccd.setCourseTypeSubclassName(typeName);*/
			List<CourseLineShow> livelist = courseDao.selectLiveList(ccdlive);
			vo.setLivelist(livelist);
		}
		
		
		result.setData(vo);
		result.setStatus("0");
		return result;
		
	}
	/**
	 * 
	 * <p>Title: getTime</p>  
	 * <p>Description: </p>  
	 * @param date1(实际添加的时间)
	 * @param date2(现在的时间)
	 * @return
	 */
	public static String getTime(Date date1, Date date2) {
		BigDecimal timesum = new BigDecimal(date2.getTime() - date1.getTime());
		BigDecimal bd = new BigDecimal(24 * 60 * 60 * 1000);
		BigDecimal day = new BigDecimal(timesum.doubleValue() / bd.doubleValue()).setScale(2, BigDecimal.ROUND_UP);
		String time1 = "";
		 if (day.compareTo(new BigDecimal(1)) == 1 && day.compareTo(new BigDecimal(2)) == -1) {
			time1 = "昨天";
		} else if (day.compareTo(new BigDecimal(0)) == 1 && day.compareTo(new BigDecimal(1)) == -1) {
			time1 = "今天";
		}else{
			time1 = Patterns.sfTime(date1);
		}
		return time1;
	}
	
	  public List<String> comments(int textStatus){
	    	
	    	List<String> text = userCommentDao.selectCommentText(textStatus);
			Random rand = new Random();
			List<String> comments=new ArrayList<String>();
			for(int i=0;i<2;i++){
				
				if(text.size()!=0){
					int num = rand.nextInt(text.size())+0;
					if(comments==null || comments.size()==0){
						comments.add(text.get(num));
						text.remove(num);////在集合中剔除已经有的对象
					}else{
						comments.add(text.get(num));
					}
				}
				
			}
			return comments;
	    	
	    }
}
