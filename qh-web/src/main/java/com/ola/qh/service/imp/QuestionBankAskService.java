package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankAskDao;
import com.ola.qh.entity.QuestionBankAsk;
import com.ola.qh.service.IQuestionBankAskService;
import com.ola.qh.util.Results;
@Service
public class QuestionBankAskService implements IQuestionBankAskService{

	@Autowired
	private QuestionBankAskDao questionBankAskDao;
	
	@Transactional
	@Override
	public Results<List<QuestionBankAsk>> questionBankAsklist(String courseTypeSubclassName, int pageNo, int pageSize) {
		
		Results<List<QuestionBankAsk>> results=new Results<List<QuestionBankAsk>>();
		
		try {
			List<QuestionBankAsk> list=questionBankAskDao.questionBankAsklist(courseTypeSubclassName, pageNo, pageSize);
			
			results.setData(list);
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

}
