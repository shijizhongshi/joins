package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserCommentDao;
import com.ola.qh.dao.UserCommentImgDao;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserComment;
import com.ola.qh.entity.UserCommentImg;
import com.ola.qh.service.IUserCommentService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class UserCommentService implements IUserCommentService {

	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private UserCommentImgDao userCommentImgDao;
	@Autowired
	private IUserService userService;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<List<UserComment>> selectShopUserComment(String shopId,String doctorId, int page) {

		Results<List<UserComment>> results = new Results<List<UserComment>>();

		try {

			int pageSize = Patterns.zupageSize;
			int pageNo = (page - 1) * pageSize;

			List<UserComment> list = userCommentDao.selectShopUserComment(shopId,doctorId, pageNo, pageSize);

			for (UserComment userComment : list) {

				if(userComment.getAddtime()!=null){
					String showtime = Patterns.sfTime(userComment.getAddtime());
					userComment.setShowtime(showtime);
					String textname=userComment.getTextName();
					String[] textName = textname.split(",");
					userComment.setTextname(textName);
					
					
				}
				
				List<UserCommentImg> imglist = userCommentImgDao.selectUserCommentImg(userComment.getId());
				userComment.setList(imglist);
			}
			

			results.setStatus("0");
			results.setData(list);
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("显示失败");
			return results;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> insertUserComment(UserComment usercomment) {

		Results<String> results = new Results<String>();
		Results<User> userResult = userService.existUser(usercomment.getUserId());
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		usercomment.setUserId(userResult.getData().getId());
		try {
			
			String textName=new String();
			
			for(int i=0;i<usercomment.getTextlist().size();i++){
				
				if(i==usercomment.getTextlist().size()-1){
					textName=textName+usercomment.getTextlist().get(i);
					
				}
				else{
					textName=textName+usercomment.getTextlist().get(i)+",";
					
				}
			}
			
			
			String commentId = KeyGen.uuid();
			usercomment.setTextName(textName);
			usercomment.setId(commentId);
			usercomment.setAddtime(new Date());
			userCommentDao.insertUserComment(usercomment);

			for (UserCommentImg usercommentimg : usercomment.getList()) {
				usercommentimg.setId(KeyGen.uuid());
				usercommentimg.setAddtime(new Date());
				usercommentimg.setCommentId(commentId);
				usercommentimg.setUserId(usercomment.getUserId());
				userCommentImgDao.insertUserCommentImg(usercommentimg);
			}
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败");
			return results;
		}
	}

	@Override
	public Results<String> insertDoctorComment(UserComment usercomment) {
		
		Results<String> results = new Results<String>();
		Results<User> userResult = userService.existUser(usercomment.getUserId());
		if("1".equals(userResult.getStatus())){
			results.setStatus("1");
			results.setMessage(userResult.getMessage());
			return results;
		}
		usercomment.setUserId(userResult.getData().getId());
		try {
			
			String textName=new String();
			
			for(int i=0;i<usercomment.getTextlist().size();i++){
				
				if(i==usercomment.getTextlist().size()-1){
					textName=textName+usercomment.getTextlist().get(i);
					
				}
				else{
					textName=textName+usercomment.getTextlist().get(i)+",";
					
				}
			}
			
			String commentId = KeyGen.uuid();
			usercomment.setTextName(textName);
			usercomment.setId(commentId);
			usercomment.setAddtime(new Date());
			userCommentDao.insertDoctorComment(usercomment);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败");
			return results;
		}
		
	}
	
	@Override
	public List<String> selectCommentText(int textStatus) {
		
		return userCommentDao.selectCommentText(textStatus);
	}

	
	
}
