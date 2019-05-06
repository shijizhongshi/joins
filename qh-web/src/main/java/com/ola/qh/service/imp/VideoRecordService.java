package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.VideoRecordDao;
import com.ola.qh.entity.VideoRecord;
import com.ola.qh.service.IVideoRecordService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class VideoRecordService implements IVideoRecordService{

	@Autowired
	private VideoRecordDao videoRecordDao;
	
	@Transactional
	@Override
	public Results<List<VideoRecord>> VideoRecordList(String userId, String courseName, String chapterName, String sectionName,int page) {
		
		Results<List<VideoRecord>> results=new Results<List<VideoRecord>>();
		
		try {
			
			int pageSize=Patterns.zupageSize;
			int pageNo=(page-1)*pageSize;
			
			List<VideoRecord> list=videoRecordDao.VideoRecordList(userId, courseName, chapterName, sectionName, pageNo, pageSize);
			
			results.setData(list);
			results.setStatus("1");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	@Transactional
	@Override
	public Results<String> insertVideoRecord(VideoRecord videoRecord) {
		
		Results<String> results=new Results<String>();
		try {
			videoRecord.setAddtime(new Date());
			videoRecord.setId(KeyGen.uuid());
			long haolong=videoRecord.getEndtime().getTime()-videoRecord.getStartTime().getTime();
			long nh = 1000 * 60 * 60;
		    long nm = 1000 * 60;
		    long ns = 1000 ;
		    long hour=haolong/nh;
		    long minute=haolong%nh/nm;
		    long second=haolong%nh%nm/ns;
		    
			videoRecord.setHowlong(hour+"时"+minute+"分"+second+"秒");
			int insert=videoRecordDao.insertVideoRecord(videoRecord);
			if(insert<=0){
				results.setStatus("1");
				return results;
			}
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

}
