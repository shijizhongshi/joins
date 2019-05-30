package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionBankAsk;

public interface QuestionBankAskDao {

	public List<QuestionBankAsk> questionBankAsklist(@Param("courseTypeSubclassName")String courseTypeSubclassName,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
}
