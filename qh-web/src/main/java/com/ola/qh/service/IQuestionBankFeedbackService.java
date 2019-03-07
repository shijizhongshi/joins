package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionBankFeedback;

public interface IQuestionBankFeedbackService {

	public List<QuestionBankFeedback> feedbackList(String userId);
	
	public int addFeedback(QuestionBankFeedback questionBankFeedback);
}
