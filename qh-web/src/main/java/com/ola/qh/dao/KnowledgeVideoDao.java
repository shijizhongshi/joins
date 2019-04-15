package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.KnowledgeVideo;

public interface KnowledgeVideoDao {

	public List<KnowledgeVideo> KnowledgeVideoList(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	
}
