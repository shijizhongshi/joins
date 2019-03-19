package com.ola.qh.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.QuestionBankUserFinishDao;
import com.ola.qh.entity.QuestionBankUserFinish;
import com.ola.qh.service.IQuestionBankUserFinishService;

@Service
public class QuestionBankUserFinishService implements IQuestionBankUserFinishService{

	@Autowired
	private QuestionBankUserFinishDao questionBankUserFinishDao;
	
	@Override
	public int addUserFinish(QuestionBankUserFinish questionBankUserFinish) {
		
		return questionBankUserFinishDao.addUserFinish(questionBankUserFinish);
	}

	@Override
	public int updateUserFinish(QuestionBankUserFinish questionBankUserFinish) {
		
		return questionBankUserFinishDao.updateUserFinish(questionBankUserFinish);
	}

}
