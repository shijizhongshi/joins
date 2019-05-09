package com.ola.qh.service;

import com.ola.qh.entity.QuestionBankTypes;
import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<QuestionBankTypes> selectQuestionBank(String subId,String userId);
	
	
	
}
