package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserCommentDao;
import com.ola.qh.dao.UserCommentImgDao;
import com.ola.qh.entity.UserComment;
import com.ola.qh.entity.UserCommentImg;
import com.ola.qh.service.IUserCommentService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;

@Service
public class UserCommentService implements IUserCommentService {

	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private UserCommentImgDao userCommentImgDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<List<UserComment>> selectShopUserComment(String shopId, int page) {

		Results<List<UserComment>> results = new Results<List<UserComment>>();

		try {

			int pageSize = Patterns.zupageSize;
			int pageNo = (page - 1) * pageSize;

			List<UserComment> list = userCommentDao.selectShopUserComment(shopId, pageNo, pageSize);

			for (UserComment userComment : list) {

				List<UserCommentImg> imglist = userCommentImgDao.selectUserCommentImg(userComment.getId());
				userComment.setList(imglist);
			}

			results.setStatus("0");
			results.setData(list);
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败");
			return results;
		}
	}

	@Override
	public Results<List<UserComment>> selectMyUserComment(String userId, int page) {

		Results<List<UserComment>> results = new Results<List<UserComment>>();

		try {

			int pageSize = Patterns.zupageSize;
			int pageNo = (page - 1) * pageSize;

			List<UserComment> list = userCommentDao.selectMyUserComment(userId, pageNo, pageSize);

			for (UserComment userComment : list) {

				List<UserCommentImg> imglist = userCommentImgDao.selectUserCommentImg(userComment.getId());
				userComment.setList(imglist);
			}

			results.setStatus("0");
			results.setData(list);
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败");
			return results;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> insertUserComment(UserComment usercomment) {

		Results<String> results = new Results<String>();

		try {
			String commentId = KeyGen.uuid();
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> deleteUserComment(String id) {

		Results<String> results = new Results<String>();

		try {

			userCommentDao.deleteUserComment(id);

			String commentId = id;
			userCommentImgDao.deleteUserCommentImg(commentId);

			results.setStatus("0");
			return results;

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("删除失败");
			return results;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> deleteAllUserComment(String userId) {

		Results<String> results = new Results<String>();

		try {
			if (userId == null) {
				results.setStatus("1");
				return results;
			}
			userCommentDao.deleteAllUserComment(userId);

			userCommentImgDao.deleteAllUserCommentImg(userId);

			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败");
			return results;
		}
	}

}
