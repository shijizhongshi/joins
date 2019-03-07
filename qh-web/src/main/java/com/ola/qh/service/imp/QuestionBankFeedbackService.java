package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.QuestionBankFeedbackDao;
import com.ola.qh.entity.QuestionBankFeedback;
import com.ola.qh.service.IQuestionBankFeedbackService;

@Service
public class QuestionBankFeedbackService implements IQuestionBankFeedbackService{

	@Autowired
	private QuestionBankFeedbackDao questionBankFeedbackDao;
	
	@Override
	public List<QuestionBankFeedback> feedbackList(String userId) {

		return questionBankFeedbackDao.feedbackList(userId);
	}

	@Override
	public int addFeedback(QuestionBankFeedback questionBankFeedback) {
		
		return questionBankFeedbackDao.addFeedback(questionBankFeedback);
	}

}
