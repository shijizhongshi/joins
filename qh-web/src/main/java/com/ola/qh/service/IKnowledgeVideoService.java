package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.util.Results;

public interface IKnowledgeVideoService {

	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo,int pageSize);
	
}
