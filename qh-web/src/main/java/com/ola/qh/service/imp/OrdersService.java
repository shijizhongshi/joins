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
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class OrdersService implements IOrdersService{

	@Autowired
	private IUserService userService;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private OrdersProductDao ordersProductDao;
	@Autowired
	private ShopDrugDao shopDrugDao;
	@Autowired
	private CourseDao courseDao;
	
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<String> submitOrders(OrdersVo ordersVo) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		try {
			/////校验用户的信息是否准确
			Results<String> userResult = userService.existUser(ordersVo.getUserId());
			if("1".equals(userResult.getStatus())){
				return userResult;
			}
				List<Orders> ordersList = ordersVo.getOrdersList();
				BigDecimal totalPrice=BigDecimal.ZERO;
				for (Orders orders : ordersList) {
					String oid = KeyGen.uuid();
					orders.setAddress(ordersVo.getAddress());
					orders.setReceiver(ordersVo.getReceiver());
					orders.setMobile(ordersVo.getMobile());
					orders.setUserId(ordersVo.getUserId());
					orders.setOrderno(String.valueOf(KeyGen.next()));
					orders.setOrdersStatus(OrdersStatus.NEW);
					orders.setOrdersType(ordersVo.getOrdersType());
					orders.setId(oid);
					orders.setAddtime(new Date());
					if(orders.getProduct()!=null && orders.getProduct().size()!=0){
						//////说明订单中有产品
						BigDecimal prices=BigDecimal.ZERO;
						List<OrdersProduct> productList = orders.getProduct();
						for (OrdersProduct ordersProduct : productList) {
							ordersProduct.setAddtime(new Date());
							ordersProduct.setUserId(ordersVo.getUserId());
							ordersProduct.setId(KeyGen.uuid());
							ordersProduct.setMuserId(orders.getMuserId());
							ordersProduct.setOrdersId(oid);
							if(ordersVo.getOrdersType()==1){
								//////通过药品的id查商品的价格等信息
								ShopDrug shopDrug = shopDrugDao.selectById(ordersProduct.getProductId());
								ordersProduct.setProductImg(shopDrug.getImgUrl());
								ordersProduct.setProductName(shopDrug.getDrugName());
								ordersProduct.setProductPrice(shopDrug.getDiscountPrice());
								//////产品实际支付的金额
								BigDecimal payout = shopDrug.getDiscountPrice().multiply(new BigDecimal(ordersProduct.getCount()));
								ordersProduct.setPayout(payout);
								prices.add(payout);
							}else if(ordersVo.getOrdersType()==0){
							   //////通过课程的id查课程的价格等信息
								Course course = courseDao.singleCourse(ordersProduct.getProductId());
								ordersProduct.setProductImg(course.getCourseImg());
								ordersProduct.setProductName(course.getCourseName());
								ordersProduct.setProductPrice(course.getCourseDiscountPrice());
								prices.add(course.getCourseDiscountPrice());
								ordersProduct.setPayout(course.getCourseDiscountPrice());
								//////count 记得传1
							}
							ordersProduct.setStatusCode(OrdersStatus.NEW);
							ordersProduct.setStatusName("新订单");
							/////保存订单商品的信息
							ordersProductDao.insertOrdersProduct(ordersProduct);
							
						}
						totalPrice.add(prices);
						orders.setPayaccount(prices);//////订单实际支付的金额
						//////保存订单的信息
						ordersDao.insertOrders(orders);
					}
					
				}
				int r = totalPrice.compareTo(ordersVo.getTotalPayout());
				if(r!=0){
					///////前端传过来的订单总金额和实际计算的不符
					result.setStatus("1");
					result.setMessage("订单总金额和实际计算的金额不符");
			        return result;
				}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("提交信息处理有误~");
			return result;
		}
	}
	
}
