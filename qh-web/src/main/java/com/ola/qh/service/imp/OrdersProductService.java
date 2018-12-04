package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.OrdersProductRefundDao;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersProductRefund;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.service.IOrdersProductService;
import com.ola.qh.service.IPayService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
/**
 * 申请退款 卖家的申请的处理   买家修改申请的操作
* @ClassName: OrdersProductService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月30日  
*
 */
@Service
public class OrdersProductService implements IOrdersProductService{

	@Autowired
	private IUserService userService;
	@Autowired
	private OrdersProductRefundDao ordersProductRefundDao;
	@Autowired
	private OrdersProductDao ordersProductDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private IPayService payService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> applyRefund(OrdersProductRefund or) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		/*try {*/
			Results<String> userResult = userService.existUser(or.getUserId());
			if("1".equals(userResult.getStatus())){
				return userResult;
			}
			String opid = or.getOrdersProductId();
			OrdersProduct op = ordersProductDao.singleOrdersProduct(opid);
			
			if(!OrdersStatus.PAID.equals(op.getStatusCode())){
				result.setStatus("1");
				result.setMessage("只有未发货的产品才可以申请退款");
				return result;
			}
			/////产品的实际支付金额和申请的退款的金额进行比较
			int num=op.getPayout().compareTo(or.getRefundMoney());
			if(num==-1){
				result.setStatus("1");
				result.setMessage("退款金额大于实际支付金额");
				return result;
				
			}
			or.setId(KeyGen.uuid());
			or.setAddtime(new Date());
			ordersProductRefundDao.insertRefund(or);
			List<OrdersProduct> oplist = ordersProductDao.selectByOid(op.getOrdersId(), null);
			//////看看订单是否就这一个产品如果是的话  修改订单的产品   如果不是的话就不修改订单的状态
			if(oplist.size()==1){
				/////修改订单的状态(订单中只有一个产品)
				ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.APPLYREFUNED, op.getStatusCode(), new Date(), null,null,null,null,null);
			}
			//////修改订单产品的状态
			ordersProductDao.updateOrdersProduct(opid, OrdersStatus.APPLYREFUNED, "申请退款", op.getStatusCode(), new Date());
			
			result.setStatus("0");
			return result;
		/*} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("申请退款失败");
			return result;
		}*/
		
	}



	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> updateRefund(String ordersProductId, String statusCode) {
		Results<String> result = new Results<String>();
		try {
			String statusName="";
			OrdersProduct op = ordersProductDao.singleOrdersProduct(ordersProductId);
			///////取消申请的退款
			if("CANCELAPPLY".equals(statusCode)){
				/////在状态是申请或者是拒绝的时候可以取消申请的操作
				if(!OrdersStatus.APPLYREFUNED.equals(op.getStatusCode()) && !OrdersStatus.REJECTREFUNED.equals(op.getStatusCode())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
			    statusCode=op.getOldStatusCode();
			    statusName="待发货的状态";
			}
			
			/////商家同意退款
			if(OrdersStatus.AGREEREFUNED.equals(statusCode)){
				statusName="同意退款";
				//////把他的退款信息改成退款成功的状态
				if(!OrdersStatus.APPLYREFUNED.equals(op.getStatusCode())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
				//////把钱直接退还给买家(完成之后改成退款成功)
				OrdersPayment orderspayment = ordersDao.singlePayment(op.getOrdersId());
				List<OrdersPayment> lisOrdersPayment = ordersDao.listByExtransno(orderspayment.getExtransno());
				OrdersProductRefund refund = ordersProductRefundDao.singleRefund(ordersProductId);
				BigDecimal money = refund.getRefundMoney();
				BigDecimal totalMoney=BigDecimal.ZERO;
				for (OrdersPayment opt:lisOrdersPayment) {
					totalMoney = totalMoney.add(opt.getMoney());
				}
				int totalfee=totalMoney.multiply(new BigDecimal(100)).intValue();
				int refundfee=money.multiply(new BigDecimal(100)).intValue();
				if(orderspayment!=null){
					Map<String,String> map =new HashMap<String, String>();
					if("ALIPAY".equals(orderspayment.getPaytypeCode())){
						///////
						map=payService.aliOrdersRefund(orderspayment.getExtransno(), money.setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
					}else if("WXPAY".equals(orderspayment.getPaytypeCode())){
						map=payService.wxOrderRefund(orderspayment.getExtransno(), totalfee, refundfee);
					}
					if("0".equals(map.get("status"))){
						statusCode=OrdersStatus.RECEIVED;/////退款成功的也就意味着退款工作已完成
						statusName="退款成功~";
					}else{
						result.setStatus("1");
						result.setMessage(map.get("error"));
						return result;
					}
				}
			}
			/////商家拒绝退款
			if(OrdersStatus.REJECTREFUNED.equals(statusCode)){
				statusName="拒绝退款";
				/////把订单的信息改成oldstatus原来的状态
				if(!OrdersStatus.APPLYREFUNED.equals(op.getStatusCode())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
			}
			///////修改申请退款的状态
			OrdersProductRefund or=new OrdersProductRefund();
			or.setOrdersProductId(ordersProductId);
			or.setStatusCode(statusCode);
			or.setUpdatetime(new Date());
			ordersProductRefundDao.updateRefund(or);
			ordersProductDao.updateOrdersProduct(ordersProductId, statusCode,statusName, null, new Date());
			if(ordersProductDao.selectByOid(op.getOrdersId(), null).size()==1){
				ordersDao.updateOrders(op.getOrdersId(), statusCode, null, new Date(), null,null,null,null,null);
			}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
	}



	@Override
	@Transactional
	public Results<String> alterRefund(OrdersProductRefund opr) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		try {
			OrdersProduct op = ordersProductDao.singleOrdersProduct(opr.getOrdersProductId());
			if(!OrdersStatus.REJECTREFUNED.equals(op.getStatusCode()) && !OrdersStatus.APPLYREFUNED.equals(op.getStatusCode())){
				//////只有拒绝和申请的订单才可以进行修改退款申请
				result.setStatus("1");
				result.setMessage("状态不符~");
				return result;
			}
			if(OrdersStatus.REJECTREFUNED.equals(op.getStatusCode())){
				//////当是被拒绝的订单的时候需要先
				opr.setStatusCode(OrdersStatus.APPLYREFUNED);
				if(ordersProductDao.selectByOid(op.getOrdersId(),null).size()==1){
					//////修改订单的信息
					ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.APPLYREFUNED, null, new Date(), null,null,null,null,null);
				}
				/////修改订单产品的信息
				ordersProductDao.updateOrdersProduct(op.getId(), OrdersStatus.APPLYREFUNED, "申请", null, new Date());
			}
			ordersProductRefundDao.updateRefund(opr);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
		
	}



	@Override
	public Results<OrdersProduct> single(String ordersProductId) {
		// TODO Auto-generated method stub
		Results<OrdersProduct> result=new Results<OrdersProduct>();
		OrdersProduct op =ordersProductDao.singleOrdersProduct(ordersProductId);
		OrdersProductRefund opr = ordersProductRefundDao.singleRefund(ordersProductId);
		op.setOpr(opr);
		result.setData(op);
		result.setStatus("0");
		return result;
	}

}
