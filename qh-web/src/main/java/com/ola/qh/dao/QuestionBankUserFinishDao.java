package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionBankUserFinish;
import com.ola.qh.vo.UserFinishDomain;

public interface QuestionBankUserFinishDao {
	
	public int showUserFinishCount(@Param("subId")String subId,@Param("userId")String userId);
	
	public int existUserFinish(@Param("userId")String userId,@Param("bankId")String bankId);
	
	public int addupdateUserFinish(UserFinishDomain userFinishDomain);

	public int addUserFinish(QuestionBankUserFinish questionBankUserFinish);
	
	public int updateUserFinish(QuestionBankUserFinish questionBankUserFinish);
}
