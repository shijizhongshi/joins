package com.ola.qh.service;

import com.ola.qh.entity.SearchCircleVo;
import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchVo;

public interface ISearchService {

	public Results<SearchVo> searchs(String searchName,String address,String status,int page);
	
	public Results<SearchCircleVo> searchCircle(String searchName,String types,int page);
}
