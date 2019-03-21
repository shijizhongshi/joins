package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankUserFinishDao;
import com.ola.qh.entity.QuestionBankUserFinish;
import com.ola.qh.entity.User;
import com.ola.qh.service.IQuestionBankUserFinishService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserFinishDomain;

@Service
public class QuestionBankUserFinishService implements IQuestionBankUserFinishService{

	@Autowired
	private QuestionBankUserFinishDao questionBankUserFinishDao;
	
	@Autowired
	private IUserService userservice;
	
	@Transactional
	@Override
	public Results<String> addupdateUserFinish(UserFinishDomain userFinishDomain) {
		
		Results<String> results=new Results<String>();
		
		Results<User> userResult = userservice.existUser(userFinishDomain.getUserId());
		
		try {
			
			/*if("1".equals(userResult.getStatus())){
				results.setStatus("1");
				results.setMessage(userResult.getMessage());
				return results;
			}
			*/
			List<QuestionBankUserFinish> userFinishlist=userFinishDomain.getList();
			
			for (QuestionBankUserFinish userFinish : userFinishlist) {
				
				userFinish.setUserId(userFinishDomain.getUserId());
				
				int exist=questionBankUserFinishDao.existUserFinish(userFinishDomain.getUserId(), userFinish.getBankId());
				
				if(exist>0){
					
					userFinish.setUpdatetime(new Date());
					questionBankUserFinishDao.updateUserFinish(userFinish);
					
				}else{
					userFinish.setId(KeyGen.uuid());
					userFinish.setAddtime(new Date());
					questionBankUserFinishDao.addUserFinish(userFinish);
					
				}
				
			}
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	
}
