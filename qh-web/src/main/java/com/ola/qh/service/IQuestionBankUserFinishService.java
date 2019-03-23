package com.ola.qh.service;

import com.ola.qh.util.Results;
import com.ola.qh.vo.UserFinishDomain;

public interface IQuestionBankUserFinishService {
	
	public Results<String> addupdateUserFinish(UserFinishDomain userFinishDomain);
	
}
