package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.KnowledgeVideoDao;
import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.util.Results;

@Service
public class KnowledgeVideoService implements IKnowledgeVideoService{

	@Autowired
	private KnowledgeVideoDao knowledgeVideoDao;
	
	@Transactional
	@Override
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(int pageNo,int pageSize,String courseTypeSubclassName) {
		
		Results<List<KnowledgeVideo>> results=new Results<List<KnowledgeVideo>>();
		try {
			
		List<KnowledgeVideo> list=knowledgeVideoDao.KnowledgeVideoList(pageNo, pageSize,courseTypeSubclassName);
		
		
		results.setData(list);
		results.setStatus("0");
		return results;
		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

}
