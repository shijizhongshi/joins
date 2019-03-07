package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionBankFeedback;

public interface QuestionBankFeedbackDao {

	public List<QuestionBankFeedback> feedbackList(@Param("userId")String userId);
	
	public int addFeedback(QuestionBankFeedback questionBankFeedback);
	
	
}
