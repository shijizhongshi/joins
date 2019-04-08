package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.QuestionBankUserFinishDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionSubcategoryService;
import com.ola.qh.util.Results;

@Service
public class QuestionSubcategoryService implements IQuestionSubcategoryService{

	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;
	
	@Autowired
	private QuestionBankDao questionBankDao;
	
	@Autowired
	private QuestionBankUserFinishDao questionBankUserFinishDao;

	@Transactional
	@Override
	public Results<List<QuestionSubCategory>> selectQuestionSubCategory(String categoryId,String userId) {
		
		Results<List<QuestionSubCategory>> results=new Results<List<QuestionSubCategory>>();
		try {
			
		List<QuestionSubCategory> list=questionSubcategoryDao.selectQuestionSubCategory( categoryId);
		
		int count=questionSubcategoryDao.countSubCategory(categoryId);
				
		for (QuestionSubCategory questionSubCategory : list) {
			
			int subcount=questionBankDao.countQuestionBank(questionSubCategory.getId());
			questionSubCategory.setCount(subcount);
			
			int userFinish=questionBankUserFinishDao.showUserFinishCount(questionSubCategory.getId(),userId);
			questionSubCategory.setUserFinishCount(userFinish);
			
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			questionSubCategory.setShowtime(sf.format(questionSubCategory.getAddtime()));
		}
		if(list==null || list.size()==0){
			
			results.setStatus("1");
			return results;
		}
		
		results.setData(list);
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
