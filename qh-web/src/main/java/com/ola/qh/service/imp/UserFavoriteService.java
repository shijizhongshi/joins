package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.UserFavoriteDao;
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
	public List<UserFavorite> selectUserFavorite(String userId,int pageNo,int pageSize,int productType) {
		
		return userFavoriteDao.selectUserFavorite(userId, pageNo, pageSize, productType);
	}

	@Override
	public Results<String> insertUserFavorite(UserFavorite userFavorite) {
		Results<String> result=new Results<String>();
		Results<String> userResult = userService.existUser(userFavorite.getUserId());
		if("1".equals(userResult.getStatus())){
			result.setMessage(userResult.getMessage());
			result.setStatus("1");
			return result;
		}
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
	public int deleteUserFavorite(String id) {
		
		return userFavoriteDao.deleteUserFavorite(id);
	}

	@Override
	public int existUserFavorite(String productId) {
		
		return userFavoriteDao.existUserFavorite(productId,null);
	}
}
