package com.ola.qh.service.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.QuestionCategoryDao;
import com.ola.qh.dao.QuestionSubcategoryDao;
import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.service.IQuestionCategoryService;
import com.ola.qh.util.Results;

@Service
public class QuestionCategoryService implements IQuestionCategoryService{

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Autowired
	private QuestionSubcategoryDao questionSubcategoryDao;
	
	@Transactional
	@Override
	public Results<List<QuestionCategory>> selectCategory(String courseTypeSubclassName,String types) {
		
		
		Results<List<QuestionCategory>> results=new Results<List<QuestionCategory>>();
		
		try {
			
		List<QuestionCategory> list=questionCategoryDao.selectCategory(courseTypeSubclassName,types);
		
		int countcate=questionCategoryDao.countCategory(courseTypeSubclassName, types);
		for (QuestionCategory questionCategory : list) {
			
			int countsub=questionSubcategoryDao.countSubCategory(questionCategory.getId());
			questionCategory.setCount(countsub);
			
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			questionCategory.setShowtime(sf.format(questionCategory.getAddtime()));
		}
		
		
		results.setCount(countcate);
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
