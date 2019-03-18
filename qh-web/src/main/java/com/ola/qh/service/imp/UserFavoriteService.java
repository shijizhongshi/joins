package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserFavorite;
import com.ola.qh.service.IUserFavoriteService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class UserFavoriteService implements IUserFavoriteService{

	@Autowired
	private UserFavoriteDao userFavoriteDao;
	@Autowired
	private IUserService userService;

	@Override
	public Results<List<UserFavorite>> selectUserFavorite(String userId,int pageNo,int pageSize,int productType) {
		Results<List<UserFavorite>> result=new Results<List<UserFavorite>>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			userId=userResult.getData().getId();
		}
		List<UserFavorite> list=userFavoriteDao.selectUserFavorite(userId, pageNo, pageSize, productType);
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	@Override
	public Results<String> insertUserFavorite(UserFavorite userFavorite) {
		Results<String> result=new Results<String>();
		Results<User> userResult = userService.existUser(userFavorite.getUserId());
		if("1".equals(userResult.getStatus())){
			result.setStatus("1");
			result.setMessage(userResult.getMessage());
			return result;
		}
		userFavorite.setUserId(userResult.getData().getId());
		int count=userFavoriteDao.existUserFavorite(userFavorite.getProductId(),userFavorite.getUserId());
		if(count>0){
			result.setMessage("您已经收藏过此宝贝了");
			result.setStatus("1");
			return result;
		}
		userFavorite.setId(KeyGen.uuid());
		userFavorite.setAddtime(new Date());
		int insert = userFavoriteDao.insertUserFavorite(userFavorite);

		if (insert <= 0) {
			result.setStatus("1");
			result.setMessage("收藏失败");
			return result;
		}
		result.setStatus("0");
		return result;
	}

	@Override
	public Results<String> deleteUserFavorite(String id,String userId,String productId) {
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
		
		userFavoriteDao.deleteUserFavorite(id, userId, productId);
		result.setStatus("0");
		return result;
	}

	@Override
	public int existUserFavorite(String productId) {
		
		return userFavoriteDao.existUserFavorite(productId,null);
	}

	@Override
	public Results<List<UserFavorite>> selectSearchUserFavorite(String userId, int pageNo, int pageSize, String productName) {
		Results<List<UserFavorite>> result=new Results<List<UserFavorite>>();
		if(userId!=null && !"".equals(userId)){
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			
			userId=userResult.getData().getId();
		}
		List<UserFavorite> list=userFavoriteDao.selectSearchUserFavorite(userId, pageNo, pageSize, productName);
		result.setStatus("0");
		result.setData(list);
		return result;
	}

	
}
