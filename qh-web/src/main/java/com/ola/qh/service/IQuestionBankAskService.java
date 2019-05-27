package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.QuestionBankAsk;
import com.ola.qh.util.Results;

public interface IQuestionBankAskService {

	public Results<List<QuestionBankAsk>> questionBankAsklist(String courseTypeSubclassName,int pageNo,int pageSize);
}
