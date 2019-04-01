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
import com.ola.qh.entity.QuestionBankTypes;
import com.ola.qh.entity.QuestionUnit;
import com.ola.qh.entity.QuestionUnitTypes;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.Results;

@Service
public class QuestionBankService implements IQuestionBankService {

	@Autowired
	private QuestionBankDao questionBankDao;

	@Transactional
	@Override
	public Results<QuestionBankTypes> selectQuestionBank(String subId) {
		
		Results<QuestionBankTypes> results=new Results<QuestionBankTypes>();
		try {
			
			List<QuestionBank> listbank=questionBankDao.selectQuestionBank(subId);
			
			QuestionBankTypes questionBankTypes=new QuestionBankTypes();
			
			int count=questionBankDao.countQuestionBank(subId);
			
			for (QuestionBank questionBank : listbank) {
				
				List<QuestionAnswer> listanswer=questionBankDao.selectQuestionAnswer(questionBank.getId());
				
				questionBank.setAnswer(listanswer);
				
				if("共用选项".equals(questionBank.getTypes())){
					
					String[] titles=questionBank.getTitles();
					titles=questionBank.getTitle().split(",");
					questionBank.setTitles(titles);
				}
				
				QuestionUnitTypes questionUnitTypes=new QuestionUnitTypes();
					
				List<QuestionUnit> listunit=questionBankDao.selectQuestionUnit(questionBank.getId());
				
				
				for (QuestionUnit questionUnit : listunit) {
					
					List<QuestionAnswer> listanswerunit=questionBankDao.selectQuestionAnswer(questionUnit.getId());
					
					questionUnit.setUnitAnswer(listanswerunit);
					
					//////////将查询出来的试题根据类型分开存放
					
					if("单选题".equals(questionUnit.getTypes())){
						
						questionUnit.setTypes("A");
						questionUnitTypes.getAList().add(questionUnit);
					}
					else if("多选题".equals(questionUnit.getTypes())){
						
						questionUnit.setTypes("B");
						questionUnitTypes.getBList().add(questionUnit);
					}
					
				}
				
				questionBank.setUnit(questionUnitTypes);
				
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				questionBank.setShowtime(sf.format(questionBank.getAddtime()));
				
				//////////将查询出来的试题根据类型分开存放
				
				if("单选题".equals(questionBank.getTypes())){
					
					questionBank.setTypes("A");
					questionBankTypes.getAList().add(questionBank);
				}
				else if("多选题".equals(questionBank.getTypes())){
					
					questionBank.setTypes("B");
					questionBankTypes.getBList().add(questionBank);
				}
				else if("共用选项".equals(questionBank.getTypes())){
	
					questionBank.setTypes("C");
					questionBankTypes.getCList().add(questionBank);
				}
				else if("共用题干".equals(questionBank.getTypes())){
	
					questionBank.setTypes("D");
					questionBankTypes.getDList().add(questionBank);
				}
			}
			
			
			
			results.setData(questionBankTypes);
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
