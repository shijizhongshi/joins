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
		BigDecimal outMoney = userwithdrawhistory.getOutMoney();
		int notzero = outMoney.compareTo(new BigDecimal(0));
		if (notzero <= 0) {
			results.setMessage("请输入提现金额");
			results.setStatus("1");
			return results;
		}
		try {

			UserBook userBooks = userBookDao.selectUserBook(userwithdrawhistory.getUserId());
			BigDecimal accountMoney = userBooks.getAccountMoney();
			int bigdecimal = accountMoney.compareTo(outMoney);
			if (bigdecimal == -1) {
				results.setMessage("账户余额不足");
				results.setStatus("1");
				return results;
			}
			BigDecimal bookMoney = accountMoney.subtract(outMoney);
			///////修改账本中的钱
			userBookDao.updateUserBook(userwithdrawhistory.getUserId(), bookMoney, new Date());
			//////保存账本的提现记录~
			userwithdrawhistory.setAddtime(new Date());
			userwithdrawhistory.setMoney(outMoney);
			userwithdrawhistory.setId(KeyGen.uuid());
			userwithdrawhistory.setPayStatus(0);
			userWithdrawHistoryDao.saveUserWithdrawHistory(userwithdrawhistory);

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

	@Override
	public int updateUserWithdrawHistorystatus(String id) {
		
		return userWithdrawHistoryDao.updateUserWithdrawHistorystatus(id);
	}

	@Override
	public int existUserWithdrawHistory(String id, int payStatus) {
		
		return userWithdrawHistoryDao.existUserWithdrawHistory(id, payStatus);
	}

}
