package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.dao.UserCommentDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserComment;
import com.ola.qh.service.IShopService;
import com.ola.qh.service.IUserCommentService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopDomain;
import com.ola.qh.vo.ShopServeDomain;
import com.ola.qh.vo.ShopVo;

@Service
public class ShopService implements IShopService {

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private ShopServeDao shopServeDao;
	@Autowired
	private IUserCommentService userCommentService;
	
	@Autowired
	private UserCommentDao userCommentDao;
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> shopSaveUpdate(Shop shop) {

		Results<String> result = new Results<String>();
		/*try {*/
			if (shop.getId() != null && !"".equals(shop.getId())) {
				/////// 修改店铺的基本信息
				shop.setUpdatetime(new Date());
				shopDao.updateShop(shop);
				if(shop.getServeDaomainList()!=null && shop.getServeDaomainList().size()!=0){
					List<String> serveList = shop.getServeDaomainList();
					String servename="";
					for (String servedomain : serveList) {
						if("".equals(servename)){
							servename=servedomain;
						}else{
							servename=servename+servedomain;
						}
					}
					shop.setServeDomain(servename);
				}
				if(shop.getImgList()!=null && shop.getImgList().size()>0){
					//////店铺执业资格的图片集合
					List<ShopImg> imgList = shop.getImgList();
					List<ShopImg> oldimgList= shopDao.selectList(shop.getId(),1);
					for (int o = 0; o < oldimgList.size(); o++) {
						int i=0;
						for (; i < imgList.size(); i++) {
							if(oldimgList.get(o).getId().equals(imgList.get(i).getId())){
								break;
							}
						}
						if(i==imgList.size()){
							///////说明这个oldimgList.get(o).getId()已经被删除了
							/////需要在数据库里也删除
							shopDao.deleteImg(oldimgList.get(o).getId());
						}
					}
					for (ShopImg shopImg : imgList) {
						if(shopImg.getId()==null || "".equals(shopImg.getId())){
							//////也就是说这是新增的图片
							shopImg.setAddtime(new Date());
							shopImg.setId(KeyGen.uuid());
							shopImg.setShopId(shop.getId());
							shopImg.setSubtype(1);
							shopDao.insertImg(shopImg);
						}
					}
				}
				if(shop.getShopType()==1 && shop.getEnvironmentImgList()!=null && shop.getEnvironmentImgList().size()>0){
					///;///如果是服务店铺的话就环境的照片
					List<ShopImg> environmentimgList = shop.getEnvironmentImgList();
					List<ShopImg> oldimgList= shopDao.selectList(shop.getId(),2);
					for (int o = 0; o < oldimgList.size(); o++) {
						int i=0;
						for (; i < environmentimgList.size(); i++) {
							if(oldimgList.get(o).getId().equals(environmentimgList.get(i).getId())){
								break;
							}
						}
						if(i==environmentimgList.size()){
							///////说明这个oldimgList.get(o).getId()已经被删除了
							/////需要在数据库里也删除
							shopDao.deleteImg(oldimgList.get(o).getId());
						}
					}
					for (ShopImg shopImg : environmentimgList) {
						if(shopImg.getId()==null || "".equals(shopImg.getId())){
							//////也就是说这是新增的图片
							shopImg.setAddtime(new Date());
							shopImg.setId(KeyGen.uuid());
							shopImg.setShopId(shop.getId());
							shopImg.setSubtype(2);
							shopDao.insertImg(shopImg);
						}
					}
				}
					result.setStatus("0");
					return result;
			}
			/////// 查用户的基本信息
			Results<User> userResult = userService.existUser(shop.getUserId());
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			shop.setUserId(userResult.getData().getId());
			///////查用户时候有店铺
			Shop listshop = shopDao.singleShop(shop.getUserId(), null, shop.getShopType(),null);
			if(listshop!=null){
				result.setStatus("1");
				result.setMessage("该用户已经开通过这种类型的店铺了");
				return result;
			}
			
			String id=KeyGen.uuid();
			if(shop.getShopType()==1){
				if(shop.getEnvironmentImgList()==null || shop.getEnvironmentImgList().size()<=0){
					result.setStatus("1");
					result.setMessage("服务店铺环境图片不能为空");
					return result;
				}else{
					for (ShopImg shopImg : shop.getEnvironmentImgList()) {
						shopImg.setId(KeyGen.uuid());
						shopImg.setShopId(id);
						shopImg.setAddtime(new Date());
						shopImg.setSubtype(2);
						shopDao.insertImg(shopImg);
					}
					
				}
			}
			
			shop.setId(id);
			shop.setAddtime(new Date());
			for (ShopImg shopImg : shop.getImgList()) {
				shopImg.setId(KeyGen.uuid());
				shopImg.setShopId(id);
				shopImg.setAddtime(new Date());
				shopImg.setSubtype(1);
				shopDao.insertImg(shopImg);
			}
			if(shop.getShopType()==1){
				if(shop.getServeDaomainList()==null || shop.getServeDaomainList().size()==0){
					result.setStatus("1");
					result.setMessage("服务店铺的服务领域不能为空");
					return result;
				}
				List<String> serveList = shop.getServeDaomainList();
				String servename="";
				for (String servedomain : serveList) {
					if("".equals(servename)){
						servename=servedomain;
					}else{
						servename=servename+","+servedomain;
					}
				}
				shop.setServeDomain(servename);/////服务领域
				
			}
			shopDao.insertShop(shop);
			/////修改用户表的userrole 0:没有店铺   1:服务店铺   2:商城店铺  3:两种店铺都有
			/*List<Shop> listshopss = shopDao.selectShopByUserId(shop.getUserId(), null, 0);
			User user= new User();
			user.setId(shop.getUserId());
			if(listshopss.size()==1){
				/////说明只有一种类型的店铺
				if(listshopss.get(0).getShopType()==1){
					/////服务 店铺
					user.setUserrole(1);
				}else if(listshopss.get(0).getShopType()==2){
					/////商城店铺
					user.setUserrole(2);
				}
			}
			if(listshopss.size()==2){
				/////两种类型的店铺都有
				user.setUserrole(3);
			}
			
			userDao.updateUser(user); 审核过了才会改这个用户的信息*/  
			
			
			result.setStatus("0");
			return result;

		/*} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("店铺信息保存失败");
			return result;
		}*/

	}

	@Override
	public Results<List<Shop>> selectShopByUserId(String userId,String shopId,int shopType) {
		// TODO Auto-generated method stub
		Results<List<Shop>> result=new Results<List<Shop>>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
		}
		
		List<Shop> shopList = shopDao.selectShopByUserId(userId, shopId,0);
		for (Shop shop : shopList) {
			List<ShopImg> imgList = shopDao.selectList(shop.getId(),1);
			if(shop.getShopType()==1){
				/////服务店铺需要查他的环境图
				List<ShopImg> enimgList = shopDao.selectList(shop.getId(),2);
				shop.setEnvironmentImgList(enimgList);
			}
			shop.setImgList(imgList);
		}
		result.setStatus("0");
		result.setData(shopList);
		return result;
	}

	@Override
	public List<Shop> listShop(ShopDomain sd) {
		// TODO Auto-generated method stub
		List<Shop> listshop=new ArrayList<Shop>();
		List<Shop> listshortshop=new ArrayList<Shop>();
		if((sd.getOrdersPrice()!=null && !"".equals(sd.getOrdersPrice()))|| (sd.getPayType()!=null && !"".equals(sd.getPayType())) || (sd.getLowprice()!=null && !"".equals(sd.getLowprice()))){
			/////按照分组查店铺的项目
			listshop=shopServeDao.selectShop(sd);
			
		}else{
			listshop=shopDao.listShop(sd);
			if(listshop.size()<12){
				listshortshop = shopDao.listShortShop(sd.getAddress(),sd.getShopType(),0,6);
			}
			
			if(listshortshop.size()!=0){
				listshop.addAll(listshortshop);
			}
			
		}
		if(sd.getShopType()==1){
			/////只有服务店铺才会有评论信息
			for (Shop shop1 : listshop) {
				double avgGrade = 0;
				if(shopDao.commentGrade(shop1.getId())!=null){
					avgGrade = shopDao.commentGrade(shop1.getId()).doubleValue();
				}
				shop1.setCommentGrade(avgGrade);
				shop1.setComments(comments(0));
			}
		}
		return listshop;
		}
	/**
	 * 服务店铺详情页的展示
	 */
	@Override
	public Results<ShopVo> singleShop(String shopId) {
		/////查用户的服务店铺的首页
		Results<ShopVo> result=new Results<ShopVo>();
		ShopVo vo=new ShopVo();
		Shop shop = shopDao.singleShop(null, shopId, 1,null);
		BeanUtils.copyProperties(shop, vo);////店铺的详情
		double avgGrade=0;
		if(shopDao.commentGrade(shopId)!=null){
			avgGrade = shopDao.commentGrade(shopId);////总的评分
		}
		List<ShopImg> shopImg = shopDao.selectList(shopId, 2);
		vo.setEnvironmentImgList(shopImg);
		vo.setAvgGrades(avgGrade);
		int count=shopDao.commentCount(shopId);
		vo.setCommentCount(count);
		ShopServeDomain ssd=new ShopServeDomain();
		ssd.setPageSize(0);
		ssd.setShopId(shopId);
		ssd.setServeStatus(1);
		ssd.setPaymentType("团购");
		List<ShopServe> groupList = shopServeDao.selectList(ssd);
		vo.setGroupList(groupList);
		ssd.setPaymentType("预约");
		List<ShopServe> reserveList = shopServeDao.selectList(ssd);
		vo.setReserveList(reserveList);
		Results<List<UserComment>> commentresult  = userCommentService.selectShopUserComment(shopId, null, 1);
		if("1".equals(commentresult.getStatus())){
			result.setStatus("1");
			result.setMessage(commentresult.getMessage());
			return result;
		}
		vo.setCommentList(commentresult.getData());
		result.setData(vo);
		result.setStatus("0");
		return result;
	}
	
	@Override
	public List<Shop> selectShopServeType() {

		return shopDao.selectShopServeType();
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
