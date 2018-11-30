package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.ola.qh.entity.OrdersDomain;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class OrdersService implements IOrdersService {

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
	public Results<List<OrdersPayment>> submitOrders(OrdersVo ordersVo) {
		// TODO Auto-generated method stub
		Results<List<OrdersPayment>> result = new Results<List<OrdersPayment>>();
		try {
			///// 校验用户的信息是否准确
			if(ordersVo.getOid()==null || "".equals(ordersVo.getOid())){
				Results<String> userResult = userService.existUser(ordersVo.getUserId());
				if ("1".equals(userResult.getStatus())) {
					result.setStatus("1");
					result.setMessage(userResult.getMessage());
					return result;
				}
				List<Orders> ordersList = ordersVo.getOrdersList();
				BigDecimal totalPrice = BigDecimal.ZERO;
				List<OrdersPayment> oplist = new ArrayList<OrdersPayment>();
				String extransno = String.valueOf(KeyGen.next18());
				for (Orders orders : ordersList) {

					OrdersPayment op = new OrdersPayment();
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
					if (orders.getProduct() != null && orders.getProduct().size() != 0) {
						////// 说明订单中有产品
						BigDecimal prices = BigDecimal.ZERO;
						List<OrdersProduct> productList = orders.getProduct();
						for (OrdersProduct ordersProduct : productList) {
							ordersProduct.setAddtime(new Date());
							ordersProduct.setUserId(ordersVo.getUserId());
							ordersProduct.setId(KeyGen.uuid());
							ordersProduct.setMuserId(orders.getMuserId());
							ordersProduct.setOrdersId(oid);
							if (ordersVo.getOrdersType() == 0) {
								////// 通过药品的id查商品的价格等信息
								ShopDrug shopDrug = shopDrugDao.selectById(ordersProduct.getProductId());
								ordersProduct.setProductImg(shopDrug.getImgUrl());
								ordersProduct.setProductName(shopDrug.getDrugName());
								ordersProduct.setProductPrice(shopDrug.getDiscountPrice());
								////// 产品实际支付的金额
								BigDecimal payout = shopDrug.getDiscountPrice()
										.multiply(new BigDecimal(ordersProduct.getCount()))
										.setScale(2, BigDecimal.ROUND_DOWN);
								ordersProduct.setPayout(payout);
								prices = prices.add(payout).setScale(2, BigDecimal.ROUND_DOWN);
							} else if (ordersVo.getOrdersType() == 1) {
								////// 通过课程的id查课程的价格等信息
								Course course = courseDao.singleCourse(ordersProduct.getProductId());
								ordersProduct.setProductImg(course.getCourseImg());
								ordersProduct.setProductName(course.getCourseName());
								ordersProduct.setProductPrice(course.getCourseDiscountPrice());
								prices = prices.add(course.getCourseDiscountPrice()).setScale(2, BigDecimal.ROUND_DOWN);
								ordersProduct.setPayout(course.getCourseDiscountPrice());
								////// count 记得传1
							}
							ordersProduct.setStatusCode(OrdersStatus.NEW);
							ordersProduct.setStatusName("新订单");
							///// 保存订单商品的信息
							ordersProductDao.insertOrdersProduct(ordersProduct);

						}
						totalPrice = totalPrice.add(prices).setScale(2, BigDecimal.ROUND_DOWN);
						orders.setPayaccount(prices);////// 订单实际支付的金额
						////// 保存订单的信息
						ordersDao.insertOrders(orders);
						op.setUserId(ordersVo.getUserId());
						op.setMuserId(orders.getMuserId());
						op.setMoney(orders.getPayaccount());
						op.setOrdersId(oid);
						op.setPaytypeCode(ordersVo.getPaytypeCode());
						op.setPaytypeName(ordersVo.getPaytypeName());
						op.setAddtime(new Date());
						op.setId(KeyGen.uuid());
						op.setExtransno(extransno);
						if (ordersVo.getOrdersType() == 1) {
							////// 购买课程
							op.setSubjectTitle("购买课程");
							op.setBodyDetail("购买课程的支付");
						} else if (ordersVo.getOrdersType() == 0) {
							///// 购买药品
							op.setSubjectTitle("购买店铺商品");
							op.setBodyDetail("购买店铺商品的支付");
						}
						op.setOrdersType(ordersVo.getOrdersType());
						ordersDao.insertOrdersPayment(op);///// 保存订单的支付信息
						oplist.add(op);/////
					}
				}
				int r = totalPrice.compareTo(ordersVo.getTotalPayout());
				if (r != 0) {
					/////// 前端传过来的订单总金额和实际计算的不符
					result.setStatus("1");
					result.setMessage("订单总金额和实际计算的金额不符");
					return result;
				}
				result.setData(oplist);
				result.setStatus("0");
				return result;
			}
			///////订单中待付款的订单立即付款的操作
			OrdersPayment opsingle= ordersDao.singlePayment(ordersVo.getOid());
			List<OrdersPayment> list = ordersDao.listByExtransno(opsingle.getExtransno());
			result.setData(list);
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

	/**
	 * 修改订单的状态
	 */
	@Override
	@Transactional
	public Results<String> updateOrders(String ordersStatus, String ordersName, String expressNo, String ordersId) {
		Results<String> result = new Results<String>();
		try {
			Date deliveredtime=null;
			Orders orders = ordersDao.singleOrders(ordersId);
			if (OrdersStatus.DELIVERED.equals(ordersStatus)) {
				//////订单发货
				if (!OrdersStatus.PAID.equals(orders.getOrdersStatus())) {
					result.setStatus("1");
					result.setMessage("订单状态不符");
					return result;
				}
				deliveredtime=new Date();
			}
			if (OrdersStatus.RECEIVED.equals(ordersStatus)) {
				//// 订单已完成
				if(!OrdersStatus.DELIVERED.equals(orders.getOrdersStatus())){
					result.setStatus("1");
					result.setMessage("订单状态不符");
					return result;
				}
			}
			//////// 修改订单的状态~~~
			List<OrdersProduct> listop = ordersProductDao.selectByOid(ordersId, orders.getOrdersStatus());
			ordersDao.updateOrders(ordersId, ordersStatus, orders.getOrdersStatus(), new Date(), expressNo,null,deliveredtime);
			////// 修改订单产品的属性
			for (OrdersProduct ordersProduct : listop) {
				ordersProductDao.updateOrdersProduct(ordersProduct.getOrdersId(), ordersStatus, ordersName,
						ordersProduct.getStatusCode(), new Date());
			}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新订单状态失败");
			return result;
		}

	}

	@Override
	public Results<OrdersDomain> singleOrders(String ordersId) {
		Results<OrdersDomain> result=new Results<OrdersDomain>();
		OrdersDomain od = new OrdersDomain();
		Orders o = ordersDao.singleOrders(ordersId);
		
		BeanUtils.copyProperties(o, od);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(o.getPaidtime()!=null){
			od.setPaidtime(sf.format(o.getPaidtime()));
		}
		if(o.getDeliveredtime()!=null){
			od.setDeliveredtime(sf.format(o.getDeliveredtime()));
		}
		List<OrdersProduct> plist = ordersProductDao.selectByOid(ordersId, o.getOrdersStatus());
		BigDecimal totalprice=BigDecimal.ZERO;
		for (OrdersProduct op : plist) {
			totalprice=totalprice.add(op.getPayout());
		}
		//////防止订单中有退款的产品
		od.setPayaccount(totalprice);
		result.setData(od);
		result.setStatus("0");
		return result;
	}

	@Override
	public List<Orders> listOrders(String status,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		/////status:0全部的订单
		//status:1待付款 
		//status:2待发货的订单  
		//status:3  待收货 
		//status:4待评价  
		//status:5退款售后
		return null;
	}
	

}
