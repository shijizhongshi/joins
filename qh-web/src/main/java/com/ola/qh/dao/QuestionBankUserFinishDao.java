package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionBankUserFinish;

public interface QuestionBankUserFinishDao {
	
	public int showUserFinishCount(@Param("subId")String subId,@Param("userId")String userId);

	public int addUserFinish(QuestionBankUserFinish questionBankUserFinish);
	
	public int updateUserFinish(QuestionBankUserFinish questionBankUserFinish);
}
