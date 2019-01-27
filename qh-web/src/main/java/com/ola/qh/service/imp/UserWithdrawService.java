package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserWeixinBindingDao;
import com.ola.qh.dao.UserWithdrawDao;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserWeixinBinding;
import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.service.IUserService;
import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserWithdrawVo;

@Service
public class UserWithdrawService implements IUserWithdrawService {

	@Autowired
	private UserWithdrawDao userWithdrawDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserBookDao userBookDao;
	@Autowired
	private UserWeixinBindingDao userBindingDao;
	
	
	

	@Override
	public List<UserWithdrawVo> selectUserWithdraw(String userId, int pageNo, int pageSize,String payStatus) {

		List<UserWithdraw> list = userWithdrawDao.selectUserWithdraw(userId, pageNo, pageSize,payStatus);
		List<UserWithdrawVo> listvo=new ArrayList<UserWithdrawVo>();
		UserWithdrawVo vo=new UserWithdrawVo();
		List<UserWithdraw> listnew=new ArrayList<UserWithdraw>();
		String yearMonth=null;
		for (UserWithdraw userWithdraw : list) {
			
			if(userWithdraw.getTypes()==2){
				UserWeixinBinding userbinding = userBindingDao.selectUserBinding(userId);
				userWithdraw.setWeixinnickname(userbinding.getNickname());
				userWithdraw.setWeixinheadimg(userbinding.getHeadimgurl());
			}
			if(userWithdraw.getAddtime()!=null){
				userWithdraw.setShowtime(Patterns.sfDetailTime(userWithdraw.getAddtime()));
				String times = sfDetailTime(userWithdraw.getAddtime());
				if(yearMonth!=null){
					String timesub=times.substring(0, 8);
					if(timesub.equals(yearMonth)){
						//////如果相等的年月
						listnew.add(userWithdraw);
					}else{
						listnew=new ArrayList<UserWithdraw>();
						vo=new UserWithdrawVo();
						yearMonth=times.substring(0, 8);
						vo.setYearMonth(yearMonth);
						listnew.add(userWithdraw);
					}
				}else{
					yearMonth=times.substring(0, 8);
					vo.setYearMonth(yearMonth);
					listnew.add(userWithdraw);
				}
			}
		}
		if(listnew!=null && listnew.size()>0){
			vo.setList(listnew);
			listvo.add(vo);
		}
		
		return listvo;
	}
	
	public static String sfDetailTime(Date time){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
    	return sf.format(time);
    }
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> saveUserWithdraw(UserWithdraw userwithdraw) {

		Results<String> results = new Results<String>();
		try {
		Results<String> userresult = userService.existUser(userwithdraw.getUserId());
		if ("1".equals(userresult.getStatus())) {
			return userresult;
		}else{
			if(userwithdraw.getTypes()==2){
				//////保证提现到微信
				int count=userBindingDao.existUserBinding(userwithdraw.getUserId());
				if(count==1){
					UserWeixinBinding userBinding=userBindingDao.selectUserBinding(userwithdraw.getUserId());
					if(userBinding.getOpenId()!=null && !"".equals(userBinding.getOpenId())){
						userwithdraw.setOpenId(userBinding.getOpenId());
						userwithdraw.setWeixinnickname(userBinding.getNickname());
					}else{
						results.setMessage("微信没有被绑定~");
						results.setStatus("1");
						return results;
					}
				}else{
					results.setMessage("微信没有被绑定~");
					results.setStatus("1");
					return results;
				}
			}
			
		}
		BigDecimal outMoney = userwithdraw.getMoney();
		int notzero = outMoney.compareTo(new BigDecimal(0));
		if (notzero <= 0) {
			results.setMessage("请输入大于0.1元提现金额");
			results.setStatus("1");
			return results;
		}
		

			UserBook userBooks = userBookDao.singleUserBook(userwithdraw.getUserId());
			BigDecimal canWithdraw = userBooks.getCanWithdraw();
			int bigdecimal = canWithdraw.compareTo(outMoney);
			if (bigdecimal == -1) {
				results.setMessage("可提现金额不足");
				results.setStatus("1");
				return results;
			}
			BigDecimal onmoney=userBooks.getOnMoney();
			BigDecimal onMoney = onmoney.add(outMoney);
			BigDecimal bookMoney = canWithdraw.subtract(outMoney);
			///////修改账本中的钱
			UserBook userbook=new UserBook();
			userbook.setCanWithdraw(bookMoney);
			userbook.setOnMoney(onMoney);
			userbook.setUserId(userwithdraw.getUserId());
			userbook.setUpdatetime(new Date());
			userBookDao.updateUserBook(userbook);
			//////保存账本的提现记录~
			userwithdraw.setAddtime(new Date());
			userwithdraw.setMoney(outMoney);
			userwithdraw.setId(KeyGen.uuid());
			userwithdraw.setPayStatus(0);
			userWithdrawDao.saveUserWithdraw(userwithdraw);
			if(userwithdraw.getTypes()==1){
				results.setMessage(userwithdraw.getAliaccount());
			}else if(userwithdraw.getTypes()==2){
				results.setMessage(userwithdraw.getWeixinnickname());
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
	public int deleteUserWithdraw(String id) {

		return userWithdrawDao.deleteUserWithdraw(id);
	}

	@Override
	public int updateUserWithdrawstatus(String id) {
		
		return userWithdrawDao.updateUserWithdrawstatus(id);
	}

	@Override
	public int existUserWithdraw(String id, int payStatus) {
		
		return userWithdrawDao.existUserWithdraw(id, payStatus);
	}

}
