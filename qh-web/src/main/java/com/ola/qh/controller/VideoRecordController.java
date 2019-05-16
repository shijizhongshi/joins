package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.VideoRecord;
import com.ola.qh.service.IVideoRecordService;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/videorecord")
public class VideoRecordController {

	@Autowired
	private IVideoRecordService videoRecordService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Results<List<VideoRecord>> VideoRecordList(@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="courseName",required=false)String courseName,@RequestParam(name="chapterName",required=false)String chapterName,
			@RequestParam(name="sectionName",required=false)String sectionName,@RequestParam(name="page",required=true)int page){
		
		return videoRecordService.VideoRecordList(userId, courseName, chapterName, sectionName,page);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Results<String> insertVideoRecord(@RequestBody @Valid VideoRecord videoRecord,BindingResult valid){
		
		return videoRecordService.insertVideoRecord(videoRecord);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Results<String> deleteVideoRecord(@RequestParam(name="id",required=true)String id){
		
		Results<String> results=new Results<String>();
		
		int delete =videoRecordService.deleteVideoRecord(id);
		if(delete<=0){
			results.setStatus("1");
			return results;
		}
		results.setStatus("0");
		return results;
	}
}
