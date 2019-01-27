package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.ISearchService;
import com.ola.qh.util.Results;
import com.ola.qh.vo.SearchProductVo;
import com.ola.qh.vo.SearchVo;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	@Autowired
	private ISearchService searchService;
	
	@RequestMapping("/products")
	public Results<SearchVo> searchs(
			@RequestParam(name="searchName",required=false)String searchName,
			@RequestParam(name="status",required=true)String status,
			@RequestParam(name="address",required=false)String address,
			@RequestParam(name="page",required=true)int page){
		
		return searchService.searchs(searchName,address, status, page);
	} 
}

