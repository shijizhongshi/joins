package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionBank;
import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<List<QuestionBank>> selectQuestionBank(String subId,int pageNo,int pageSize);
	
	
	
}
