package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugCartDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopDrugImgDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopDrugImg;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.ShopCountVo;
import com.ola.qh.vo.ShopDrugDomain;
import com.ola.qh.vo.ShopDrugVo;

@Service
public class ShopDrugService implements IShopDrugService {

	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private ShopDrugImgDao shopDrugImgDao;

	@Autowired
	private IUserService userService;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrdersProductDao ordersProductDao;
	@Autowired
	private UserFavoriteDao userFavoriteDao;
	@Autowired
	private ShopDrugCartDao shopDrugCartDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> insertDrug(ShopDrug shopDrug) {
		Results<String> result = new Results<String>();
		result = userService.existUser(shopDrug.getUserId());
		if ("1".equals(result.getStatus())) {
			return result;
		}
		Shop shop = shopDao.singleShop(shopDrug.getUserId(), shopDrug.getShopId(),0);
		/////// 必须是2商城店铺才有上传药品的权限
		if (shop.getShopType()==1) {
			result.setStatus("1");
			result.setMessage("店铺的类型不对");
			return result;
		}
		/*try {*/
			String id = KeyGen.uuid();
			if (shopDrug.getImgList() != null && shopDrug.getImgList().size() != 0) {
				/////// 集合不等于空的话
				shopDrug.setImgUrl(shopDrug.getImgList().get(0).getImgUrl());
				for (ShopDrugImg sdi : shopDrug.getImgList()) {
					////// 保存药品图片的信息
					sdi.setId(KeyGen.uuid());
					sdi.setDrugId(id);
					sdi.setAddtime(new Date());
					shopDrugImgDao.insertDrugImg(sdi);

				}
			}
			///// 保存药品的信息
			shopDrug.setId(id);
			shopDrug.setAddtime(new Date());
			shopDrugDao.insertDrug(shopDrug);
			result.setStatus("0");
			return result;
			/*} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}*/

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> updateDrug(ShopDrug shopDrug) {
		Results<String> result = new Results<String>();
		if(shopDrug.getId()==null || "".equals(shopDrug.getId())){
			result.setStatus("1");
			result.setMessage("修改对象的id不能为空");
			return result;
		}
		try {
			shopDrug.setUpdatetime(new Date());
			/////参数传过来的图片集合
			List<ShopDrugImg> imglist = shopDrug.getImgList();
			//////当传过来的图片不为空的时候进行修改图片
			if(imglist!=null && imglist.size()!=0){
				//////保证商品的主图是修改后的图片
				shopDrug.setImgUrl(imglist.get(0).getImgUrl());	
				/////数据库中的图片集合
				List<ShopDrugImg> originalimgList = shopDrugImgDao.listDrugImg(shopDrug.getId());
				for(int i=0;i<originalimgList.size();i++){
					int j=0;
					for(;j<imglist.size();j++){
						if(originalimgList.get(i).getId().equals(imglist.get(j).getId())){
							///////说明这个id的图片没有被删除
							break;
						}
					}
					if(j==imglist.size()){
						shopDrugImgDao.deleteDrugImg(originalimgList.get(i).getId());
					}
				}
				for (int j = 0; j < imglist.size(); j++){
					if (imglist.get(j).getId() == null || "".equals(imglist.get(j).getId()))
					{
						///////说明是新增的图片没有id
						imglist.get(j).setDrugId(shopDrug.getId());
						imglist.get(j).setId(KeyGen.uuid());
						imglist.get(j).setAddtime(new Date());
						shopDrugImgDao.insertDrugImg(imglist.get(j));
					}
			    }
			}
			if(shopDrug.getStatus()!=0){
				//////也就是说修改产品的状态
				/////失效收藏的商品   失效购物车的商品
				shopDrugCartDao.updateShopDrugCart(0, null, new Date(),1,shopDrug.getId());
				
				userFavoriteDao.update(shopDrug.getId(), 1);
			}
			shopDrugDao.updateDrug(shopDrug);
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
	public Results<ShopDrug> selectById(String drugId,String userId) {
		Results<ShopDrug> result = new Results<ShopDrug>();
		ShopDrug sd = shopDrugDao.selectById(drugId);
		if(userId!=null && !"".equals(userId)){
			///////
			Results<String> userresult = userService.existUser(userId);
			if("1".equals(userresult.getStatus())){
				result.setStatus("1");
				result.setMessage(userresult.getMessage());
				return result;
			}
			int count = userFavoriteDao.existUserFavorite(drugId, userId);
			if(count>0){
				sd.setIsFavorite(1);
			}
		}
		
		List<ShopDrugImg> listimg = shopDrugImgDao.listDrugImg(drugId);
		sd.setImgList(listimg);
		result.setData(sd);
		result.setStatus("0");
		return result;
	}

	@Override
	/**
	 * 药品的集合~~~
	 * <p>Title: selectDrugList</p>  
	 * <p>Description: </p>  
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Results<ShopDrugVo> selectDrugList(ShopDrugDomain sdd) {
		// TODO Auto-generated method stub
		Results<ShopDrugVo> result = new Results<ShopDrugVo>();
		List<ShopDrug> list = new ArrayList<ShopDrug>();
		List<ShopDrug> list2 = new ArrayList<ShopDrug>();
		ShopDrugVo vo = new ShopDrugVo();
		if(sdd.getStatus()==1){
			///////店铺的首页
		/////店铺首页展示(分推荐列表和促销列表)
			sdd.setIsrecommend(1);////推荐列表
			list = shopDrugDao.selectDrugList(sdd);
			
			sdd.setIsrecommend(0);
			sdd.setIssales(1);////促销列表
			list2 = shopDrugDao.selectDrugList(sdd);
			
			
		}else if(sdd.getStatus()==0){
			/////查全部的商品(按照类别查)正售中的
			list = shopDrugDao.selectDrugList(sdd);
			
		}else if(sdd.getStatus()==2){
			//////下架的商品
			list = shopDrugDao.selectDrugList(sdd);
			
		}else if(sdd.getStatus()==4){
			////审批中的  未审批的
			list = shopDrugDao.selectDrugList(sdd);
			
		}
		if(list!=null && list.size()!=0){
			Shop shop = shopDao.singleShop(null, list.get(0).getShopId(), 2);
			
			vo.setShopId(shop.getId());
			vo.setShopAddress(shop.getAddress());
			vo.setShopDoorUrl(shop.getDoorHeadUrl());
			vo.setShopLogo(shop.getShopLogo());
			vo.setShopName(shop.getShopName());
			vo.setList(list);
			vo.setSalesList(list2);
		}
		
		result.setData(vo);
		result.setStatus("0");
		return result;
	}

	@Override
	public Results<ShopCountVo> shopCount(String shopId,int types) {
		// TODO Auto-generated method stub
		Results<ShopCountVo> result=new Results<ShopCountVo>();
		ShopCountVo vo=new ShopCountVo();
		////正售的商品的个数
			int salesCount = shopDrugDao.selectDrugListCount(shopId,0,types);
			vo.setSalesCount(salesCount);
			/////下架的商品
			int downCount = shopDrugDao.selectDrugListCount(shopId,2,types);
			vo.setDownCount(downCount);
			////待审核的商品
			int wlimitCount = shopDrugDao.selectDrugListCount(shopId,4,types);
			vo.setWlimitCount(wlimitCount);
		
		result.setStatus("0");
		result.setData(vo);
		return result;
	}

}
