package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionUnit;

public interface QuestionBankDao {

	public List<QuestionBank> selectQuestionBank(@Param("subId")String subId,@Param("page")int pageNo,@Param("pageSize")int pageSize);
	
	public QuestionBank singleQuestionBank(@Param("id")String id);
	
	public int countQuestionBank(@Param("subId")String subId);
	
	public List<QuestionAnswer> selectQuestionAnswer(String bankUnitId);
	
	public List<QuestionUnit> selectQuestionUnit(String bankId);
	
}
