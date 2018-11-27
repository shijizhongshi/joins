package com.ola.qh.service.imp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopDrugImgDao;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopDrugImg;
import com.ola.qh.service.IShopDrugService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> insertDrug(ShopDrug shopDrug) {
		Results<String> result = new Results<String>();
		result = userService.existUser(shopDrug.getUserId());
		if ("1".equals(result.getStatus())) {
			return result;
		}
		List<Shop> shoplist = shopDao.selectShopByUserId(shopDrug.getUserId(), shopDrug.getShopId());
		/////// 必须是商城店铺才有上传药品的权限
		if (shoplist.size() != 1 && shoplist.get(0).getShopType() == 2) {
			result.setStatus("1");
			result.setMessage("店铺的类型不对");
			return result;
		}
		try {
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
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
		}

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
	public Results<ShopDrug> selectById(String drugId) {
		Results<ShopDrug> result = new Results<ShopDrug>();
		ShopDrug sd = shopDrugDao.selectById(drugId);
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
	public Results<List<ShopDrug>> selectDrugList(String shopId,int pageNo, int pageSize,int ishot) {
		// TODO Auto-generated method stub
		Results<List<ShopDrug>> result = new Results<List<ShopDrug>>();
		List<ShopDrug> list = shopDrugDao.selectDrugList(shopId,pageNo, pageSize,ishot);
		for(int i=0;i<list.size();i++){
			List<ShopDrugImg> listimg = shopDrugImgDao.listDrugImg(list.get(i).getId());
			list.get(i).setImgList(listimg);
		}
		result.setData(list);
		result.setStatus("0");
		return result;
	}

}