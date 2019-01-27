package com.ola.qh.service;

import java.util.List;

import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchProductVo;
import com.ola.qh.vo.SearchVo;

public interface ISearchService {

	public Results<SearchVo> searchs(String searchName,String address,String status,int page);
}
