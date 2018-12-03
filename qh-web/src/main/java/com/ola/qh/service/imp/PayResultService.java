package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.PayResult;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IPayResultService;
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
	private UserBuyCourseDao ubcDao;
	
	@Autowired
	private CourseDao courseDao;
	
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
		/*try {*/
			for (OrdersPayment op:oplist) {
				Orders orders=ordersDao.singleOrders(op.getOrdersId());
				ordersDao.updateOrdersPayment(op.getId(), 1, new Date(),null,null);
				List<OrdersProduct> listOrdersProduct=ordersProductDao.selectByOid(op.getOrdersId(), orders.getOrdersStatus());
				ordersDao.updateOrders(op.getOrdersId(), OrdersStatus.PAID, orders.getOrdersStatus(), new Date(),null,new Date(),null,null,null);
				for (OrdersProduct orderproduct : listOrdersProduct) {
					ordersProductDao.updateOrdersProduct(orderproduct.getId(), OrdersStatus.PAID, "支付成功", orderproduct.getStatusCode(), new Date());
				}
			}
			/////保存回调成功的信息
			pr.setComment("支付成功之后店铺商品的回调成功");
            pr.setPayStatus("Success");
			ordersDao.insertPayResult(pr);
			
	/*	} catch (Exception e) {
			// TODO: handle exception
			//////保存回调失败的信息
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pr.setComment("支付成功之后店铺商品的回调失败");
            pr.setPayStatus("failure");
			ordersDao.insertPayResult(pr);
		}*/
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
					Course course  = courseDao.singleCourse(orderproduct.getProductId());
					UserBuyCourse ubc = new UserBuyCourse();
					ubc.setAddtime(new Date());
					ubc.setId(KeyGen.uuid());
					ubc.setCourseId(orderproduct.getProductId());
					ubc.setCourseDiscountPrice(course.getCourseDiscountPrice());
					ubc.setCourseImgUrl(course.getCourseImg());
					ubc.setCourseName(course.getCourseName());
					ubc.setCoursePrice(course.getCoursePrice());
					ubc.setOrdersId(orderproduct.getOrdersId());
					ubc.setUserId(orderproduct.getUserId());
					ubcDao.insertUserCourse(ubc);
				}
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
	
	
	

}
