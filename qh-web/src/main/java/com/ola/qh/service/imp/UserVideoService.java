package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.DoctorReplyDao;
import com.ola.qh.dao.DoctorsDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.dao.UserVideoDao;
import com.ola.qh.entity.Doctors;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserLikes;
import com.ola.qh.entity.UserVideo;
import com.ola.qh.entity.UserVideoComment;
import com.ola.qh.service.IUserService;
import com.ola.qh.service.IUserVideoService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
@Service
public class UserVideoService implements IUserVideoService{

	@Autowired
	private UserVideoDao userVideoDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private DoctorsDao doctorDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private DoctorReplyDao doctorReplyDao;
	
	@Override
	public Results<String> save(UserVideo uv) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		result = userService.existUser(uv.getUserId());
		if("1".equals(result.getStatus())){
			return result;
		}
		User user = userService.sinleUser(uv.getUserId(), null);
		if(user.getIsdoctor()!=1 && user.getUserrole()==0){
			/////说明既不是医师护也不是店铺没有权限发送小视频
			result.setStatus("1");
			result.setMessage("您没有上传小视频的权限");
			return result;
			
		}
		if(user.getIsdoctor()==1){
			
			Doctors d=doctorDao.singleDoctors(null, user.getId(), "1");
			if(d!=null){
				uv.setHeadImgUrl(d.getHeadImg());
				uv.setNickname(d.getName());
				uv.setProfessional(d.getProfessional());
				uv.setDoctorId(d.getId());//////这个是医生的id
			}
			
		}else{
			Shop s=new Shop();
			if(user.getUserrole()==1){
				////服务店铺
				s=shopDao.singleShop(uv.getUserId(), null, 1, null);
				
			}else if(user.getUserrole()==2){
				////商城店铺
				s=shopDao.singleShop(uv.getUserId(), null, 2, null);
				
			}else{
				//////两种店铺都有(取商城店铺的名称)
				s=shopDao.singleShop(uv.getUserId(), null, 2, null);
				
			}
			uv.setShopId(s.getId());
			uv.setShopname(s.getShopName());
		}
		
		uv.setId(KeyGen.uuid());
		uv.setAddtime(new Date());
		userVideoDao.insert(uv);
		result.setStatus("0");//////保存用户上传的视频成功
		return result;
	}

	@Override
	public List<UserVideo> list(String userId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<UserVideo> list = userVideoDao.list(userId, pageNo, pageSize);
		if(userId!=null && userId!=""){
			for (UserVideo userVideo : list) {
				UserLikes ul = doctorReplyDao.singleLikes(userId, userVideo.getId());
				if(ul!=null){
					/////说明已经点过赞了
					userVideo.setIslikes(1);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 点赞的操作
	 */
	@Transactional
	@Override
	public Results<String> update(String userId,String id,int likeNumber,int types) {
		// TODO Auto-generated method stub
		Results<String> result=new Results<String>();
		UserLikes ul = doctorReplyDao.singleLikes(userId,id);
		UserVideo uv=new UserVideo();
		if(ul!=null){
			/////说明已经点过赞了(取消点赞)
			doctorReplyDao.deleteLikes(ul.getId());
			if(types==1){
				uv.setId(id);
				uv.setLikeNumber(String.valueOf(likeNumber-1));
				userVideoDao.update(uv);
			}else if(types==2){
				userVideoDao.updateComment(String.valueOf(likeNumber-1), new Date(), id);
			}
			
		}else{
			////说明没有点赞(添加点赞)
			doctorReplyDao.insertLikes(KeyGen.uuid(), userId, id, new Date());
			if(types==1){
			uv.setId(id);
			uv.setLikeNumber(String.valueOf(likeNumber+1));
			userVideoDao.update(uv);
			}else if(types==2){
				userVideoDao.updateComment(String.valueOf(likeNumber+1), new Date(), id);
			}
		}
		result.setStatus("0");
		return result;
	}

	@Transactional
	@Override
	public Results<String> insertComment(UserVideoComment vc) {
		// TODO Auto-generated method stub
		Results<String> result = userService.existUser(vc.getUserId());
		if("1".equals(result.getStatus())){
			return result;
		}
		
		vc.setId(KeyGen.uuid());
		vc.setAddtime(new Date());
		//////////修改视频的评论个数
		UserVideo uvold = userVideoDao.single(vc.getVid());
		if(uvold!=null){
			UserVideo uv=new UserVideo();
			uv.setId(vc.getVid());
			uv.setCommentNumber(String.valueOf(Integer.valueOf(uvold.getCommentNumber())+1));
			uv.setUpdatetime(new Date());
			//////修改视频的评论个数
			userVideoDao.update(uv);
			///////添加视频的评论
			if(vc.getTypes()==2){
				if(vc.getCommentid()==null || "".equals(vc.getCommentid())){
					result.setStatus("1");
					result.setMessage("回复对应的评论id不能为空");
					return result;
				}
				////////不能自己回复自己
				UserVideoComment uvc = userVideoDao.singleComment(vc.getCommentid());
				if(uvc!=null){
					if(uvc.getUserId().equals(vc.getUserId())){
						result.setStatus("1");
						result.setMessage("不能自己回复自己");
						return result;
					}
				}
				
			}
			userVideoDao.insertComment(vc);
			return result;
		}else{
			result.setStatus("1");
			result.setMessage("视频的标识的id不存在");
			return result;
		}
		
	}

	@Override
	public List<UserVideoComment> listComment(String vid,String userId,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<UserVideoComment> list = userVideoDao.listComment(vid, null, pageNo, pageSize,1);
		for (UserVideoComment userVideoComment : list) {
			String showtime = Patterns.sfDetailTime(userVideoComment.getAddtime());
			userVideoComment.setShowtime(showtime);
			if(userId!=null && !"".equals(userId)){
				UserLikes ul = doctorReplyDao.singleLikes(userId,userVideoComment.getId());
				if(ul!=null){
					userVideoComment.setIslike(1);/////说明这个用户已经点过赞了
				}
			}
			///////评论对应的回复的集合
			List<UserVideoComment> replylist = userVideoDao.listComment(vid, userVideoComment.getId(), 0, 0,2);
			for (UserVideoComment userVideoComment2 : replylist) {
				String replyshowtime = Patterns.sfDetailTime(userVideoComment2.getAddtime());
				userVideoComment2.setShowtime(replyshowtime);
			}
			userVideoComment.setReplylist(replylist);
		}
		return list;
	}



}
