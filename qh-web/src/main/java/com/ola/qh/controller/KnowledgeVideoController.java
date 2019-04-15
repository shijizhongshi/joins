package com.ola.qh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.KnowledgeVideo;
import com.ola.qh.service.IKnowledgeVideoService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping(value="/api/KnowledgeVideo")
public class KnowledgeVideoController {

	@Autowired
	private IKnowledgeVideoService knowledgeVideoService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<KnowledgeVideo>> KnowledgeVideoList(){
		
		return knowledgeVideoService.KnowledgeVideoList();
	}
	
	
}
