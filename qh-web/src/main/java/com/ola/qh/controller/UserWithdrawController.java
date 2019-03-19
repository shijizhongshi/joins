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
import com.ola.qh.entity.UserWithdraw;
import com.ola.qh.service.IUserWeixinBindingService;
import com.ola.qh.service.IUserWithdrawService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.UserWithdrawVo;

/**
 * 
 * 
 * @ClassName: UserWithdrawHistoryController
 * @Description: 用户提现的增删查
 * @author guozihan
 * @date 2018/12/7
 *
 */
@RestController
@RequestMapping(value = "/api/withdraw")
public class UserWithdrawController {

	@Autowired
	private IUserWithdrawService userWithdrawService;
	

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Results<List<UserWithdrawVo>> selectUserWithdrawHistory(
			@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name="payStatus",required=false)String payStatus,
			@RequestParam(name = "page", required = true) int page) {


		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		return userWithdrawService.selectUserWithdraw(userId, pageNo,
				pageSize,payStatus);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Results<String> saveUserWithdrawHistory(@RequestBody @Valid UserWithdraw userwithdraw,
			BindingResult valid) {

		Results<String> results = new Results<String>();
		if (valid.hasErrors()) {
			results.setMessage("信息填写不完整,请检查");
			results.setStatus("1");
			return results;
		}
		if(userwithdraw.getTypes()==1){
			if(userwithdraw.getAliaccount()==null || "".equals(userwithdraw.getAliaccount())){
				results.setMessage("支付宝的账号不能为空");
				results.setStatus("1");
				return results;
			}
			if(userwithdraw.getRealname()==null || "".equals(userwithdraw.getRealname())){
				results.setMessage("真实姓名不能为空");
				results.setStatus("1");
				return results;
			}
		}
		if(userwithdraw.getTypes()!=1 && userwithdraw.getTypes()!=2){
			results.setMessage("请选择提现到微信或者支付宝");
			results.setStatus("1");
			return results;
		}
		return userWithdrawService.saveUserWithdraw(userwithdraw);
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Results<String> deleteUserWithdrawHistory(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "payStatus", required = true) int payStatus) {

		Results<String> results = new Results<String>();

		int exist = userWithdrawService.existUserWithdraw(id, payStatus);
		if (exist==0) {
			results.setMessage("账单信息不存在");
			results.setStatus("1");
			return results;

		}
		if (payStatus != 0) {
			int update = userWithdrawService.updateUserWithdrawstatus(id);
			if (update <= 0) {
				results.setMessage("删除失败1");
				results.setStatus("1");
				return results;
			}
			results.setStatus("0");
			return results;

		}
		int delete = userWithdrawService.deleteUserWithdraw(id);
		if (delete <= 0) {
			results.setMessage("删除失败");
			results.setStatus("1");
			return results;
		}
		results.setMessage("删除成功");
		results.setStatus("0");
		return results;
	}
}
