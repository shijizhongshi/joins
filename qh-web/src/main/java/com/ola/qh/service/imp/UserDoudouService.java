package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserDouDou;
import com.ola.qh.service.IUserDoudouService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class UserDoudouService implements IUserDoudouService{

	@Autowired
	private UserBookDao userBookDao;
	@Autowired
	private IUserService userService;
	
	@Override
	@Transactional
	public Results<String> insertDoudou(UserDouDou udd) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		Results<String> usserresult = userService.existUser(udd.getUserId());
		if("1".equals(usserresult.getStatus())){
			return usserresult;
		}
		UserBook ub = userBookDao.singleUserBook(udd.getUserId());
		if(ub!=null){
			UserBook ubnew=new UserBook();
			ubnew.setAccountDoudou(ub.getAccountDoudou()+udd.getDoudou());
			ubnew.setCanuseDoudou(ub.getCanuseDoudou()+udd.getDoudou());
			ubnew.setUpdatetime(new Date());
			ubnew.setUserId(udd.getUserId());
			userBookDao.updateUserBook(ubnew);
			//////账本中的豆豆增加
		}
		////////豆豆记录表增加
		udd.setId(KeyGen.uuid());
		udd.setAddtime(new Date());
		userBookDao.insertDoudou(udd);
		
		result.setStatus("0");
		return result;
	}

	@Override
	public List<UserDouDou> listDoudou(String userId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return userBookDao.listDoudou(userId, pageNo, pageSize);
	}

}
