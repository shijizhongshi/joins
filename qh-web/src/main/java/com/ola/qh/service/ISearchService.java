package com.ola.qh.service;

import java.util.List;

import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchProductVo;

public interface ISearchService {

	public Results<List<SearchProductVo>> searchs(String searchName,String address,String status,int page);
}
