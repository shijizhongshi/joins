package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionUnit;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.Results;

@Service
public class QuestionBankService implements IQuestionBankService {

	@Autowired
	private QuestionBankDao questionBankDao;

	@Transactional
	@Override
	public Results<List<QuestionBank>> selectQuestionBank(String subId) {
		
		Results<List<QuestionBank>> results=new Results<List<QuestionBank>>();
		try {
			
			List<QuestionBank> listbank=questionBankDao.selectQuestionBank(subId);
			
			int count=questionBankDao.countQuestionBank(subId);
			
			for (QuestionBank questionBank : listbank) {
				
				List<QuestionAnswer> listanswer=questionBankDao.selectQuestionAnswer(questionBank.getId());
				
				questionBank.setAnswer(listanswer);
				
				List<QuestionUnit> listunit=questionBankDao.selectQuestionUnit(questionBank.getId());
				
				for (QuestionUnit questionUnit : listunit) {
					
					List<QuestionAnswer> listanswerunit=questionBankDao.selectQuestionAnswer(questionUnit.getId());
					
					questionUnit.setUnitAnswer(listanswerunit);
				}
				questionBank.setUnit(listunit);
				
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				questionBank.setShowtime(sf.format(questionBank.getAddtime()));
			}
			
			
			
			results.setData(listbank);
			results.setCount(count);
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	
	

}
