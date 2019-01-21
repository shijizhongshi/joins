package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.dao.UserMessageDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.PayResult;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.entity.UserMessage;
import com.ola.qh.service.IPayResultService;
import com.ola.qh.service.IPayService;
import com.ola.qh.service.IPushService;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Bytes;
import com.ola.qh.util.KeyGen;
/**
 * 支付成功的回调
* @ClassName: PayResultService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月29日  
*
 */
@Service
public class PayResultService implements IPayResultService{

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private OrdersProductDao ordersProductDao;
	
	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	
	@Autowired
	private IStoreService storeService;
	
	@Autowired
	private UserBookDao userBookDao;
	
	@Autowired
	private IPushService pushService;
	
	@Autowired
	private UserMessageDao messageDao;
	
	/**
	 * 店铺商品的购买
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void payDrugResults(List<OrdersPayment> oplist) {
		// TODO Auto-generated method stub
		PayResult pr= new PayResult();
		pr.setAddtime(new Date());
		pr.setExtransno(oplist.get(0).getExtransno());
		pr.setId(KeyGen.uuid());
		pr.setUserId(oplist.get(0).getUserId());
		try {
			for (OrdersPayment op:oplist) {
				Orders orders=ordersDao.singleOrders(op.getOrdersId());
				//////修改商家的待结算金额(添加)
				UserBook ub = userBookDao.singleUserBook(orders.getMuserId());
				ordersDao.updateOrdersPayment(op.getId(), 1, new Date(),null,null);
				List<OrdersProduct> listOrdersProduct=ordersProductDao.selectByOid(op.getOrdersId(), orders.getOrdersStatus());
				ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.PAID, orders.getOrdersStatus(), new Date(),null,new Date(),null,null,null);
				BigDecimal money=BigDecimal.ZERO;
				for (OrdersProduct orderproduct : listOrdersProduct) {
					ordersProductDao.updateOrdersProduct(orderproduct.getId(), OrdersStatus.PAID, "支付成功", orderproduct.getStatusCode(), new Date());
					money=money.add(orderproduct.getPayout()).setScale(2, BigDecimal.ROUND_DOWN);
				}
				
				if(ub!=null){
					BigDecimal waitMoney=ub.getFortheMoney().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					UserBook userBook=new UserBook();
					userBook.setFortheMoney(waitMoney);///待结算金额
					userBook.setUserId(orders.getMuserId());////
					userBook.setUpdatetime(new Date());
					userBookDao.updateUserBook(userBook);
				}
				//////////给商家推送一个发货的通知消息
				pushService.send(orders.getMuserId(), "商品订单","您的销售订单中有一个新的订单请及时发货");
				/////////给商家保存一个消息
				UserMessage um=new UserMessage();
				um.setId(KeyGen.uuid());
				um.setDescribe("您的销售订单中有一个新的订单请及时发货");
				um.setHeadStatus(0);
				um.setOrdersId(orders.getId());
				um.setTitle("商品订单");
				um.setTypes(1);
				um.setUserId(orders.getMuserId());
				um.setSubType(2);
				messageDao.insert(um);
				
			}
			/////保存回调成功的信息
			pr.setComment("支付成功之后店铺商品的回调成功");
            pr.setPayStatus("Success");
			ordersDao.insertPayResult(pr);
			
		} catch (Exception e) {
			// TODO: handle exception
			//////保存回调失败的信息
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setComment("支付成功之后店铺商品的回调失败");
            pr.setPayStatus("failure");
			ordersDao.insertPayResult(pr);
		}
	}

	@Override
	public void payCourseResults(List<OrdersPayment> oplist) {
		// TODO Auto-generated method stub
		PayResult pr= new PayResult();
		pr.setAddtime(new Date());
		pr.setExtransno(oplist.get(0).getExtransno());
		pr.setId(KeyGen.uuid());
		pr.setUserId(oplist.get(0).getUserId());
		try {
			for (OrdersPayment op:oplist) {
				Orders orders=ordersDao.singleOrders(op.getOrdersId());
				ordersDao.updateOrdersPayment(op.getId(), 1, new Date(),null,null);
				List<OrdersProduct> listOrdersProduct=ordersProductDao.selectByOid(op.getOrdersId(), orders.getOrdersStatus());
				ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.RECEIVED, orders.getOrdersStatus(), new Date(),null,new Date(),null,null,null);
				for (OrdersProduct orderproduct : listOrdersProduct) {
					ordersProductDao.updateOrdersProduct(orderproduct.getId(), OrdersStatus.RECEIVED, "支付成功订单完成~", orderproduct.getStatusCode(), new Date());
					///////课程的操作(形成我的课程的信息)
					UserBuyCourse ubc = new UserBuyCourse();
					ubc.setAddtime(new Date());
					ubc.setId(KeyGen.uuid());
					if(orders.getClassStatus()==1){
						ubc.setClassId(orderproduct.getProductId());
					}else{
						ubc.setCourseId(orderproduct.getProductId());
					}
					
					ubc.setCourseDiscountPrice(orderproduct.getProductPrice());
					ubc.setCourseImgUrl(orderproduct.getProductImg());
					ubc.setCourseName(orderproduct.getProductName());
					ubc.setOrdersId(op.getOrdersId());
					ubc.setUserId(orderproduct.getUserId());
					userBuyCourseDao.insertUserCourse(ubc);
				}
				pushService.send(orders.getUserId(), "课程订单","购买成功,请及时到我的-我的课程及时学习");
				/////////给商家保存一个消息
				UserMessage um=new UserMessage();
				um.setId(KeyGen.uuid());
				um.setDescribe("购买成功,请及时到我的-我的课程及时学习");
				um.setHeadStatus(0);
				um.setOrdersId(orders.getId());
				um.setTitle("课程订单");
				um.setTypes(3);
				um.setUserId(orders.getUserId());
				messageDao.insert(um);
			}
			/////保存回调成功的信息
			pr.setComment("支付成功之后的课程回调成功");
            pr.setPayStatus("Success");
			ordersDao.insertPayResult(pr);
			
		} catch (Exception e) {
			// TODO: handle exception
			//////保存回调失败的信息
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setComment("支付成功之后的课程回调失败");
            pr.setPayStatus("failure");
			ordersDao.insertPayResult(pr);
		}
	}

	@Override
	public void payServeResults(List<OrdersPayment> oplist) {
		// TODO Auto-generated method stub
		PayResult pr= new PayResult();
		pr.setAddtime(new Date());
		pr.setExtransno(oplist.get(0).getExtransno());
		pr.setId(KeyGen.uuid());
		pr.setUserId(oplist.get(0).getUserId());
		try {
			for (OrdersPayment op:oplist) {
				Orders orders=ordersDao.singleOrders(op.getOrdersId());
				///////修改支付成功的状态值(防止重复性的回调)
				ordersDao.updateOrdersPayment(op.getId(), 1, new Date(),null,null);
				List<OrdersProduct> listOrdersProduct=ordersProductDao.selectByOid(op.getOrdersId(), orders.getOrdersStatus());
				///服务已经购买但是没有使用~
				byte[] bytes = Bytes.qrcode(op.getOrdersId(), 300, 300);
				String fname = KeyGen.gen() + ".jpg";
				String url  = storeService.storeUrl(fname, bytes);
				ordersDao.updateQrcode(op.getOrdersId(), url);///将生成的带参数的二维码保存起来
				ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.PAID, orders.getOrdersStatus(), new Date(),null,new Date(),null,null,null);
				BigDecimal money=BigDecimal.ZERO;
				for (OrdersProduct orderproduct : listOrdersProduct) {
					ordersProductDao.updateOrdersProduct(orderproduct.getId(), OrdersStatus.PAID, "支付成功未使用", orderproduct.getStatusCode(), new Date());
					money=money.add(orderproduct.getPayout()).setScale(2, BigDecimal.ROUND_DOWN);
				}
				UserBook ub = userBookDao.singleUserBook(orders.getMuserId());
				if(ub!=null){
					BigDecimal waitMoney=ub.getFortheMoney().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					UserBook userBook=new UserBook();
					userBook.setFortheMoney(waitMoney);///待结算金额
					userBook.setUserId(orders.getMuserId());////
					userBook.setUpdatetime(new Date());
					userBookDao.updateUserBook(userBook);
				}
			}
			
			
			pr.setComment("支付成功之后服务项目的回调成功");
            pr.setPayStatus("Success");
			ordersDao.insertPayResult(pr);
			
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setComment("支付成功之后的服务回调失败");
            pr.setPayStatus("failure");
			ordersDao.insertPayResult(pr);
		}
	}
	
	
	

}
