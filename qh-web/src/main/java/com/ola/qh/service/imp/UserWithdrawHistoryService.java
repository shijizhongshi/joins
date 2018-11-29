package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserWithdrawHistoryDao;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserWithdrawHistory;
import com.ola.qh.service.IUserService;
import com.ola.qh.service.IUserWithdrawHistoryService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class UserWithdrawHistoryService implements IUserWithdrawHistoryService {

	@Autowired
	private UserWithdrawHistoryDao userWithdrawHistoryDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserBookDao userBookDao;

	@Override
	public List<UserWithdrawHistory> selectUserWithdrawHistory(String userId, int pageNo, int pageSize) {

		return userWithdrawHistoryDao.selectUserWithdrawHistory(userId, pageNo, pageSize);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> saveUserWithdrawHistory(UserWithdrawHistory userwithdrawhistory) {

		Results<String> results = new Results<String>();

		Results<String> userresult = userService.existUser(userwithdrawhistory.getUserId());
		if ("1".equals(userresult.getStatus())) {
			return userresult;
		}
		try {

			UserBook userBooks = userBookDao.selectUserBook(userwithdrawhistory.getUserId());
			BigDecimal accountMoney = userBooks.getAccountMoney();
			BigDecimal onMoney = userwithdrawhistory.getOnMoney();
			int bigdecimal = accountMoney.compareTo(onMoney);
			if (bigdecimal == -1) {
				results.setMessage("账户余额不足");
				results.setStatus("1");
				return results;
			}
			UserBook userBook = new UserBook();
			BigDecimal bookMoney = accountMoney.subtract(onMoney);
			userBook.setAccountMoney(bookMoney);
			userBook.setUpdatetime(new Date());
			userBook.setUserId(userwithdrawhistory.getUserId());
			userBookDao.updateUserBook(userBook);

			UserWithdrawHistory userwithdrawhistory1 = new UserWithdrawHistory();
			userwithdrawhistory1.setId(KeyGen.uuid());
			userwithdrawhistory1.setAddtime(new Date());
			userwithdrawhistory1.setMoney(onMoney);
			userwithdrawhistory1.setUserId(userwithdrawhistory.getUserId());
			userwithdrawhistory1.setWithdrawTypes(userwithdrawhistory.getWithdrawTypes());
			userWithdrawHistoryDao.saveUserWithdrawHistory(userwithdrawhistory1);

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
	public int deleteUserWithdrawHistory(String id) {

		return userWithdrawHistoryDao.deleteUserWithdrawHistory(id);
	}

}
