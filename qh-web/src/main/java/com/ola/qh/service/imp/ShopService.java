package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopImg;
import com.ola.qh.service.IShopService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class ShopService implements IShopService {

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private IUserService userService;
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> shopSaveUpdate(Shop shop) {

		Results<String> result = new Results<String>();
		/*try {*/
			if (shop.getId() != null && !"".equals(shop.getId())) {
				/////// 修改店铺的基本信息
				shop.setUpdatetime(new Date());
				shopDao.updateShop(shop);
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
			Results<String> userResult = userService.existUser(shop.getUserId());
			if("1".equals(userResult.getStatus())){
				return userResult;
			}
			///////查用户时候有店铺
			List<Shop> listshop = shopDao.selectShopByUserId(shop.getUserId(), null, shop.getShopType());
			if(listshop.size()==1){
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
			
			
			shopDao.insertShop(shop);
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
	public List<Shop> selectShopByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Shop> shopList = shopDao.selectShopByUserId(userId, null,0);
		for (Shop shop : shopList) {
			List<ShopImg> imgList = shopDao.selectList(shop.getId(),1);
			if(shop.getShopType()==1){
				/////服务店铺需要查他的环境图
				List<ShopImg> enimgList = shopDao.selectList(shop.getId(),2);
				shop.setEnvironmentImgList(enimgList);
			}
			
			shop.setImgList(imgList);
		}
		return shopList;
	}

}
