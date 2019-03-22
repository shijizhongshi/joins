package com.ola.qh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.DigestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.CourseLineCCresult;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.service.ICourseService;
import com.ola.qh.service.IUserVideoService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.LiveShowResultsVo;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

@RestController
@RequestMapping("/api/video")
public class UserVideoController {

	@Autowired
	private IUserVideoService userVideoService;
	@Autowired
	private ICourseService courseService;
	/**
	 * 视频的保存
	 * @param uv
	 * @param valid
	 * @return
	 * @throws DigestException 
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Results<String> save(@RequestBody @Valid UserVideo uv,BindingResult valid) throws DigestException{
		Results<String> result=new Results<String>();
		
		if(valid.hasErrors()){
			result.setStatus("1");
			result.setMessage("短视频信息填写不完整");
			return result;
		}
		return userVideoService.save(uv);
		
	}
	
	@RequestMapping(value="/notify",method=RequestMethod.GET)
	public String ccnotifyUrl(
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="videoid",required=true)String videoid,
			@RequestParam(name="status",required=true)String status,
			@RequestParam(name="duration",required=true)String duration,
			@RequestParam(name="image",required=true)String image,HttpServletResponse response){
		
		if("OK".equals(status)){
			if(userVideoService.existVideo(videoid)==0){
				UserVideo uv=new UserVideo();
				uv.setAddtime(new Date());
				uv.setFirstImage(image);
				uv.setId(KeyGen.uuid());
				String userIds=String.valueOf(userId.substring(0, userId.indexOf("title")));
				uv.setUserId(userIds);
				String title=String.valueOf(userId.substring(userId.indexOf("title")+5));
				uv.setTitle(title);
				uv.setVideoId(videoid);
				userVideoService.saveUV(uv);
			}
			StringBuilder sbuilder1 = new StringBuilder();
			sbuilder1.append("<?xml version='1.0' encoding='UTF-8' ?>").append("<video>OK</video>");
			try (PrintWriter writer = response.getWriter())
		    {
			writer.print(sbuilder1.toString());
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sbuilders="<?xml version='1.0' encoding='UTF-8' ?> <video>OK</video>";
			return sbuilders;
			
		}
		return null;
		
		
	}
	/////////直播开始的回调
	@RequestMapping(value="/notify/lineShow/start",method=RequestMethod.GET)
	public String liveShowStart(
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="roomId",required=true)String roomId,
			@RequestParam(name="liveId",required=true)String liveId,
			@RequestParam(name="type",required=true)String type,
			@RequestParam(name="recordId",required=false)String recordId,
			@RequestParam(name="startTime",required=false)String startTime,
			@RequestParam(name="endTime",required=false)String endTime,
			@RequestParam(name="stopStatus",required=false)String stopStatus,
			@RequestParam(name="recordStatus",required=false)String recordStatus,
			@RequestParam(name="recordVideoId",required=false)String recordVideoId,
			@RequestParam(name="recordVideoDuration",required=false)String recordVideoDuration,
			@RequestParam(name="replayUrl",required=false)String replayUrl,
			@RequestParam(name="offlineStatus",required=false)String offlineStatus,
			@RequestParam(name="offlineMd5",required=false)String offlineMd5,
			@RequestParam(name="offlineUrl",required=false)String offlineUrl,
			HttpServletResponse response) throws ParseException{
		
			CourseLineShow liveShow=courseService.singleLiveShow(liveId);
			if(liveShow!=null){
				//////将直播开启为开始直播的状态(修改成直播中)
				CourseLineShow cls=new CourseLineShow();
				CourseLineCCresult ccresult=new CourseLineCCresult();
				ccresult.setAddtime(new Date());
				ccresult.setId(KeyGen.uuid());
				ccresult.setLiveId(liveId);
				ccresult.setRoomId(roomId);
				ccresult.setType(type);
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(startTime!=null && !"".equals(startTime)){
					ccresult.setStartTime(sf.parse(startTime));
				}
				if(endTime!=null && !"".equals(endTime)){
					ccresult.setEndTime(sf.parse(endTime));
				}
				cls.setId(liveShow.getId());
				cls.setUpdatetime(new Date());
				if("1".equals(type)){
				//////直播开始
					cls.setStatus(1);
					ccresult.setDescribes("直播开始");
				}else if("2".equals(type)){
					/////直播结束
					cls.setStatus(2);
					if(startTime!=null && !"".equals(startTime)){
						cls.setAddtime(sf.parse(startTime));
					}
					if(endTime!=null && !"".equals(endTime)){
						cls.setEndtime(sf.parse(endTime));
					}
					
					ccresult.setStopStatus(stopStatus);
					
					
					if("10".equals(stopStatus)){
						ccresult.setDescribes("直播正常结束");
					}else{
						ccresult.setDescribes("直播非正常结束");
					}
					
				}else if("103".equals(type)){
					/////录制完成线上回放
					cls.setStatus(3);
					cls.setLiveBackId(recordId);
					
					ccresult.setRecordId(recordId);
					ccresult.setDescribes("录制完成可以进行回放直播了");
					ccresult.setRecordId(recordId);
					ccresult.setRecordStatus(recordStatus);
					ccresult.setRecordVideoDuration(recordVideoDuration);
					ccresult.setRecordVideoId(recordVideoId);
					ccresult.setReplayUrl(replayUrl);
					
				}else if("200".equals(type)){
					/////离线回放完成
					cls.setStatus(4);
					cls.setOfflineUrl(offlineUrl);
					
					ccresult.setRecordId(recordId);
					ccresult.setOfflineMd5(offlineMd5);
					ccresult.setOfflineStatus(offlineStatus);
					ccresult.setOfflineUrl(offlineUrl);
					if("10".equals(offlineStatus)){
						ccresult.setDescribes("可用的离线包");
					}else{
						ccresult.setDescribes("不可用的离线包");
					}
					
				}else{
					if("101".equals(type)){
						
						ccresult.setRecordId(recordId);
						ccresult.setDescribes("直播录制回调开始");
						ccresult.setRecordId(recordId);
						ccresult.setRecordStatus(recordStatus);
						ccresult.setRecordVideoDuration(recordVideoDuration);
						ccresult.setRecordVideoId(recordVideoId);
						ccresult.setReplayUrl(replayUrl);
						courseService.insertCCresult(ccresult);
						return null;
					}else if("102".equals(type)){
						///////保存一下直播的状态
						ccresult.setRecordId(recordId);
						ccresult.setDescribes("直播录制回调结束");
						ccresult.setRecordId(recordId);
						ccresult.setRecordStatus(recordStatus);
						ccresult.setRecordVideoDuration(recordVideoDuration);
						ccresult.setRecordVideoId(recordVideoId);
						ccresult.setReplayUrl(replayUrl);
						courseService.insertCCresult(ccresult);
						return null;
					}else{
						return null;
					}
				}
				
				courseService.insertCCresult(ccresult);
				courseService.updateListShow(cls);
				
				LiveShowResultsVo vo=new LiveShowResultsVo();
				vo.setResult("OK");
				String results=null;
				try (PrintWriter writer = response.getWriter())
			    {
				results=Json.to(vo);
				writer.print(Json.to(vo));
			    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return results;
			}
			
			return null;
		
		
		
	}
/*	public static void main(String[] args) throws IOException {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("<?xml version='1.0' encoding='UTF-8' ?>");
		
		sbuilder.append("<video>OK</video>");
		

		System.out.println(sbuilder.toString());
	}*/
	  
	/**
	 * 视频的集合
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public Results<List<UserVideo>> list(
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="page",required=true)int page,
			@RequestParam(name="types",required=false)int types){
		
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		return userVideoService.list(userId, pageNo, pageSize,types);
		
	}
	
	/**
	 * 视频点赞的操作
	 * @param userId
	 * @param id(视频的id,评论的id)
	 * @param likeNumber
	 * @param types==1是指视频的点赞   types==2的时候是指评论的点赞
	 * @return
	 */
	@RequestMapping("/update")
	public Results<String> update(
			@RequestParam(name="userId",required=true)String userId,
			@RequestParam(name="id",required=true)String id,
			@RequestParam(name="likeNumber",required=true)int likeNumber,
			@RequestParam(name="types",required=true)int types){
		
		return userVideoService.update(userId, id, likeNumber,types);
	}
	
	
	/**
	 * 评论的保存
	 * @param vc
	 * @param valid
	 * @return
	 */
	@RequestMapping(value="/save/comment",method=RequestMethod.POST)
	public Results<String> saveComment(@RequestBody @Valid UserVideoComment vc,BindingResult valid){
		Results<String> result=new Results<String>();
		
		if(valid.hasErrors()){
			result.setStatus("1");
			result.setMessage("短视频评论和回复填写不完整");
			return result;
		}
		return userVideoService.insertComment(vc);
		
	} 
	
	/**
	 * 视频的评论和回复的集合
	 * @param vid
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping("/list/comment")
	public Results<List<UserVideoComment>> listcomment(
			@RequestParam(name="vid",required=true)String vid,
			@RequestParam(name="userId",required=false)String userId,
			@RequestParam(name="page",required=true)int page){
		
		int pageSize=Patterns.zupageSize;
		int pageNo=(page-1)*pageSize;
		return userVideoService.listComment(vid,userId,pageNo,pageSize);
		
	}
	
	
	
}
