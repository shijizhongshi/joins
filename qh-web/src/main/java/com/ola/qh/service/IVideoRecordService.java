package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.VideoRecord;
import com.ola.qh.util.Results;

public interface IVideoRecordService {

	public Results<List<VideoRecord>> VideoRecordList(String userId,String courseName,String chapterName,String sectionName,int page);
	
	public Results<String> insertVideoRecord(VideoRecord videoRecord);
	
	public int deleteVideoRecord(String id);
}
