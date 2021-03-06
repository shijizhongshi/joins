package com.ola.qh.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.ShopDrugCartDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopDrugCart;
import com.ola.qh.entity.User;
import com.ola.qh.service.IShopDrugCartService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.CartVo;

@Service
public class ShopDrugCartService implements IShopDrugCartService{

	@Autowired
	private ShopDrugCartDao shopDrugCartDao;
	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private IUserService userService;
	
	@Override
	public Results<List<CartVo>> selectShopDrugCart(String userId, int page) {
		List<CartVo> volist = new ArrayList<CartVo>();
		List<CartVo> newvolist = new ArrayList<CartVo>();
		Results<List<CartVo>> result=new Results<List<CartVo>>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
		}
		List<ShopDrugCart> sdcList = shopDrugCartDao.selectShopDrugCart(userId, 0, 0);
		sdcList.forEach(sdc -> {
			String sname = sdc.getShopName();
			
		Optional<CartVo> optionalvo = volist.stream()
				.filter(vo -> {return sname.equals(vo.getShopName());})
				.findAny();
		CartVo vo = new CartVo();
		vo.setShopName(sname);
	   /////如果muserId存在的话直接返回再次循环   
		if(optionalvo.isPresent()){
			vo =optionalvo.get();
			vo.getCartlist().add(sdc);
			volist.add(vo);
		}else{
		/////如果不存在的话就赋予对象~
			vo.getCartlist().add(sdc);
			volist.add(vo);
		}
				});
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		if(volist!=null ){
			if(volist.size()<=page*pageSize){
				newvolist= volist.subList(pageNo, volist.size());
			}else{
				newvolist= volist.subList(pageNo, pageSize);
			}
			result.setData(newvolist.subList(0, newvolist.size()-1));
			result.setStatus("0");
			return result;
		}
		return null;
	}

	@Override
	@Transactional
	public Results<String> insertShopDrugCart(ShopDrugCart shopDrugCart) {
		
		Results<String> results= new Results<String>();
		Results<User> userResult = userService.existUser(shopDrugCart.getUserId());
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		shopDrugCart.setUserId(userResult.getData().getId());
		try {
			if(shopDrugCart.getUserId().equals(shopDrugCart.getMuserId())){
				results.setMessage("不可以将自己的产品加入购物车");
				results.setStatus("1");
				return results;
			}
			ShopDrug shopDrug = shopDrugDao.selectById(shopDrugCart.getDrugId());
			if(shopDrug!=null && shopDrug.getStatus()!=0){
				//////0是正常状态的商品
				results.setMessage("该商品已下架~");
				results.setStatus("1");
				return results;
				
			}
			ShopDrugCart single=shopDrugCartDao.singleShopDrugCart(shopDrugCart.getDrugId(), shopDrugCart.getUserId());
			if(single!=null){
				int count=single.getCount()+1;
				String id=single.getId();
				Date updatetime=new Date();
				shopDrugCartDao.updateShopDrugCart(count, id, updatetime,0,null);
				
				results.setStatus("0");
				return results;
				
			}
			shopDrugCart.setId(KeyGen.uuid());
			shopDrugCart.setAddtime(new Date());
			shopDrugCartDao.insertShopDrugCart(shopDrugCart);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setMessage("保存出错");
			results.setStatus("1");
			return results;
		}
		
	}

	@Override
	public Results<String> deleteShopDrugCart(String id,String userId) {
		Results<String> result=new Results<String>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
		}
		shopDrugCartDao.deleteShopDrugCart(id,userId);
		result.setStatus("0");
		return result;
	}

	@Override
	public int updateShopDrugCart(int count, String id,Date updatetime) {
		
		return shopDrugCartDao.updateShopDrugCart(count, id,updatetime,0,null);
	}


	
}
