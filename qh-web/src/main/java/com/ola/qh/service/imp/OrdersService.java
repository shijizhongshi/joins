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

import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.ShopDrugCartDao;
import com.ola.qh.dao.ShopDrugDao;
import com.ola.qh.dao.ShopServeDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.dao.UserFavoriteDao;
import com.ola.qh.dao.UserIntomoneyHistoryDao;
import com.ola.qh.dao.UserMessageDao;
import com.ola.qh.dao.UserWithdrawDao;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.ShopDrug;
import com.ola.qh.entity.ShopServe;
import com.ola.qh.entity.User;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserDouDou;
import com.ola.qh.entity.UserIntomoneyHistory;
import com.ola.qh.entity.UserMessage;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IPayService;
import com.ola.qh.service.IPushService;
import com.ola.qh.service.IUserService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.OrdersProductDomain;
import com.ola.qh.vo.OrdersCartDomain;
import com.ola.qh.vo.OrdersCountVo;
import com.ola.qh.vo.OrdersDomain;
import com.ola.qh.vo.OrdersVo;

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
	
	@Autowired
	private CourseClassDao courseClassDao;
	
	@Autowired
	private UserBookDao userBookDao;
	@Autowired
	private ShopServeDao shopServeDao;

	@Autowired
	private UserIntomoneyHistoryDao userIntomoneyHistoryDao;
	@Autowired
	private ShopDrugCartDao shopDrugCartDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private IPushService pushService;
	@Autowired
	private UserFavoriteDao userFavoriteDao;
	@Autowired
	private UserMessageDao messageDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Results<List<OrdersPayment>> submitOrders(OrdersCartDomain ordersVo) {
		// TODO Auto-generated method stub
		Results<List<OrdersPayment>> result = new Results<List<OrdersPayment>>();
		/* try { */
		///// 校验用户的信息是否准确
		if (ordersVo.getOid() == null || "".equals(ordersVo.getOid())) {
			Results<User> userResult = userService.existUser(ordersVo.getUserId());
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			ordersVo.setUserId(userResult.getData().getId());
			List<Orders> ordersList = ordersVo.getOrdersList();
			BigDecimal totalPrice = BigDecimal.ZERO;
			int count = 0;
			int totaldoudou=0;
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
					BigDecimal freight = BigDecimal.ZERO;
					List<OrdersProduct> productList = orders.getProduct();
					for (OrdersProduct ordersProduct : productList) {
						if (ordersVo.getUserId().equals(orders.getMuserId())) {
							result.setStatus("1");
							result.setMessage("不能购买自己的商品");
							return result;
						}
						if (ordersProduct.getCartId() != null && !"".equals(ordersProduct.getCartId())) {
							////// 如果购物车的ID存在的话就把这个产品在购物车中删除
							shopDrugCartDao.deleteShopDrugCart(ordersProduct.getCartId(), null);
						}

						ordersProduct.setAddtime(new Date());
						ordersProduct.setUserId(ordersVo.getUserId());
						ordersProduct.setId(KeyGen.uuid());
						ordersProduct.setMuserId(orders.getMuserId());
						ordersProduct.setOrdersId(oid);
						count += ordersProduct.getCount();
						if (ordersVo.getOrdersType() == 0) {
							////// 通过药品的id查商品的价格等信息
							ShopDrug shopDrug = shopDrugDao.selectById(ordersProduct.getProductId());
							/////// 修改商品的库存
							if (shopDrug.getStatus() != 0) {
								result.setStatus("1");
								result.setMessage("商品已失效");
								return result;

							}
							if (shopDrug.getStocks() < ordersProduct.getCount()) {
								///// 说明库存不足哦
								result.setStatus("1");
								result.setMessage("库存不足");
								return result;
							}
							shopDrugDao.updateCount(shopDrug.getStocks() - ordersProduct.getCount(),
									ordersProduct.getProductId(), ordersProduct.getCount(), null);
							ordersProduct.setFreight(shopDrug.getFreight());
							ordersProduct.setProductImg(shopDrug.getImgUrl());
							ordersProduct.setProductName(shopDrug.getDrugName());
							ordersProduct.setProductPrice(shopDrug.getDiscountPrice());
							////// 产品实际支付的金额
							BigDecimal payout = BigDecimal.ZERO;
							payout = shopDrug.getDiscountPrice().multiply(new BigDecimal(ordersProduct.getCount()))
									.setScale(2, BigDecimal.ROUND_DOWN);
							if (shopDrug.getFreight().compareTo(BigDecimal.ZERO) == 1) {
								payout = payout.add(shopDrug.getFreight()).setScale(2, BigDecimal.ROUND_DOWN);
							}
							freight = freight.add(shopDrug.getFreight()).setScale(2, BigDecimal.ROUND_DOWN);
							ordersProduct.setPayout(payout);
							prices = prices.add(payout).setScale(2, BigDecimal.ROUND_DOWN);
						} else if (ordersVo.getOrdersType() == 1) {
							////// 通过课程的id查课程的价格等信息
							
							if(ordersVo.getClassStatus()==1){
								/////说明购买了全套的课程()
								CourseClass classs = courseClassDao.classSingle(ordersProduct.getProductId());
								ordersProduct.setProductImg(classs.getImgUrl());
								ordersProduct.setProductName(classs.getClassName());
								ordersProduct.setProductPrice(classs.getClassDiscountPrice());
								prices = prices.add(classs.getClassDiscountPrice()).setScale(2, BigDecimal.ROUND_DOWN);
								ordersProduct.setPayout(classs.getClassDiscountPrice());
								totaldoudou=totaldoudou+classs.getMaxdoudou();
							}else{
								Course course = courseDao.singleCourse(ordersProduct.getProductId());
								ordersProduct.setProductImg(course.getCourseImg());
								ordersProduct.setProductName(course.getCourseName());
								ordersProduct.setProductPrice(course.getCourseDiscountPrice());
								prices = prices.add(course.getCourseDiscountPrice()).setScale(2, BigDecimal.ROUND_DOWN);
								ordersProduct.setPayout(course.getCourseDiscountPrice());
								totaldoudou=totaldoudou+course.getMaxdoudou();
							}
							
							////// count 记得传1
						} else if (ordersVo.getOrdersType() == 2) {
							/////// 这个是服务店铺项目的的购买的流程(名称/图片/价格/实际支付)
							ShopServe ss = shopServeDao.selectSingle(ordersProduct.getProductId());
							ordersProduct.setProductImg(ss.getImgUrl());
							ordersProduct.setProductName(ss.getServeName());
							ordersProduct.setProductPrice(ss.getDiscountPrice());
							BigDecimal payout = ss.getDiscountPrice().multiply(new BigDecimal(ordersProduct.getCount()))
									.setScale(2, BigDecimal.ROUND_DOWN);
							prices = prices.add(payout).setScale(2, BigDecimal.ROUND_DOWN);
							ordersProduct.setPayout(payout);

						}
						ordersProduct.setStatusCode(OrdersStatus.NEW);
						ordersProduct.setStatusName("新订单");
						///// 保存订单商品的信息
						ordersProductDao.insertOrdersProduct(ordersProduct);

					}
					totalPrice = totalPrice.add(prices).setScale(2, BigDecimal.ROUND_DOWN);
					orders.setPayaccount(prices);////// 订单实际支付的金额
					orders.setCount(count);
					orders.setFreight(freight);///// 订单总运费
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
					} else if (ordersVo.getOrdersType() == 2) {
						////// 购买服务店铺的项目
						op.setSubjectTitle("购买服务项目");
						op.setBodyDetail("购买服务项目的支付");
					}
					op.setOrdersType(ordersVo.getOrdersType());
					ordersDao.insertOrdersPayment(op);///// 保存订单的支付信息
					oplist.add(op);/////
				}
			}
			OrdersDomain od=new OrdersDomain();
			od.setUserId(ordersVo.getUserId());
			od.setOrdersType(String.valueOf(ordersVo.getOrdersType()));
			od.setPageNo(0);
			od.setPageSize(1);
			if(ordersVo.getUsedoudou()>0 ){
				UserBook ub = userBookDao.singleUserBook(ordersVo.getUserId());
				//////用户用的doudou /////允许使用豆豆的上限
				//////删除订单的信息
				List<Orders> orders = ordersDao.ordersList(od);
				///////上传刚刚保存的订单信息
				ordersDao.deleteOrders(orders.get(0).getId());
				if(ordersVo.getUsedoudou()>totaldoudou){
					result.setStatus("1");
					result.setMessage("使用豆豆的数量超过豆豆可使用豆豆的上~");
					return result;
				}
				if(ordersVo.getUsedoudou()>Integer.valueOf(ub.getCanuseDoudou())){
					result.setStatus("1");
					result.setMessage("使用豆豆的数量超过豆豆可使用豆豆的上~");
					return result;
				}
				if(ordersVo.getUsedoudou()<=totaldoudou && ordersVo.getUsedoudou()<=Integer.valueOf(ub.getCanuseDoudou())){
				/////动豆豆的兑换记录
					UserDouDou udd=new UserDouDou();
					udd.setId(KeyGen.uuid());
					udd.setAddtime(new Date());
					udd.setDoudou(ordersVo.getUsedoudou());
					udd.setStatus(2);////2兑换的
					udd.setDescribe("兑换课程使用豆豆");
					udd.setUserId(ordersVo.getUserId());
					userBookDao.insertDoudou(udd);
					
					UserBook ubnew=new UserBook();
					ubnew.setUserId(ordersVo.getUserId());
					ubnew.setUseredDoudou(String.valueOf(ordersVo.getUsedoudou()+Integer.valueOf(ub.getUseredDoudou())));
					ubnew.setCanuseDoudou(String.valueOf(Integer.valueOf(ub.getCanuseDoudou())-ordersVo.getUsedoudou()));
					ubnew.setUpdatetime(new Date());
					userBookDao.updateUserBook(ubnew);
					
				}
				
				
			}
			int r = totalPrice.compareTo(ordersVo.getTotalPayout());
			if (r != 0) {
				 /////// 前端传过来的订单总金额和实际计算的不符
			    //////删除订单的信息
				List<Orders> orders = ordersDao.ordersList(od);
				///////上传刚刚保存的订单信息
				ordersDao.deleteOrders(orders.get(0).getId());
				result.setStatus("1");
				result.setMessage("订单总金额和实际计算的金额不符");
				return result;
			}
			result.setData(oplist);
			result.setStatus("0");
			return result;
		}
		/////// 订单中待付款的订单立即付款的操作(传订单的id和支付方式即可)
		OrdersPayment opsingle = ordersDao.singlePayment(ordersVo.getOid());
		/////////// 如果支付方式不变的话修改支付方式
		if (!ordersVo.getPaytypeCode().equals(opsingle.getPaytypeCode())) {
			////// 修改支付方式
			ordersDao.updateOrders(opsingle.getOrdersId(), null, null, new Date(), null, null, null,
					ordersVo.getPaytypeCode(), ordersVo.getPaytypeName());
			ordersDao.updateOrdersPayment(opsingle.getId(), 0, new Date(), ordersVo.getPaytypeCode(),
					ordersVo.getPaytypeName());
		}
		List<OrdersPayment> list = ordersDao.listByExtransno(opsingle.getExtransno());
		result.setData(list);
		result.setStatus("0");
		return result;

		/*
		 * } catch (Exception e) { // TODO: handle exception
		 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly()
		 * ; result.setStatus("1"); result.setMessage("提交信息处理有误~"); return
		 * result; }
		 */
	}

	/**
	 * 修改商品订单的状态
	 */
	@Override
	@Transactional
	public Results<String> updateOrders(String ordersStatus, String ordersName, String expressNo, String ordersId) {
		Results<String> result = new Results<String>();
		try {
			Date deliveredtime = null;
			Orders orders = ordersDao.singleOrders(ordersId);
			if (orders.getOrdersType() == 0) {
				if (OrdersStatus.DELIVERED.equals(ordersStatus) && orders.getOrdersType() == 0) {
					////// 订单发货
					if (!OrdersStatus.PAID.equals(orders.getOrdersStatus())) {
						result.setStatus("1");
						result.setMessage("订单状态不符");
						return result;
					}
					deliveredtime = new Date();
				}
				List<OrdersProduct> listop = ordersProductDao.selectByOid(ordersId, orders.getOrdersStatus());

				////// 确认收货的页面
				if (OrdersStatus.RECEIVED.equals(ordersStatus) && orders.getOrdersType() == 0) {
					//// 订单已完成
					if (!OrdersStatus.DELIVERED.equals(orders.getOrdersStatus())) {
						result.setStatus("1");
						result.setMessage("订单状态不符");
						return result;
					}
					UserBook ub = userBookDao.singleUserBook(orders.getMuserId());
					if (ub == null) {
						result.setStatus("1");
						result.setMessage("商家的账本不能为空");
						return result;
					}
					////// 总的钱数 可提现的钱数
					BigDecimal money = BigDecimal.ZERO;
					for (OrdersProduct ordersProduct : listop) {
						money = money.add(ordersProduct.getPayout());
					}
					///// 钱得到账本里
					UserIntomoneyHistory uh = new UserIntomoneyHistory();
					uh.setAddtime(new Date());
					uh.setIntoStatus(0);
					uh.setId(KeyGen.uuid());
					uh.setMoney(money);
					uh.setOrderId(ordersId);
					////// 往商家店铺里打钱~~~
					uh.setUserId(orders.getMuserId());
					userIntomoneyHistoryDao.saveUserIntomoneyHistory(uh);

					/// 修改总账本
					BigDecimal accountMoney = ub.getAccountMoney().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal canWithdraw = ub.getCanWithdraw().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal waitMoney = ub.getFortheMoney().subtract(money).setScale(2, BigDecimal.ROUND_DOWN);
					UserBook userbook = new UserBook();
					userbook.setAccountMoney(accountMoney);//// 总的金额
					userbook.setCanWithdraw(canWithdraw);/// 可提现金额
					userbook.setFortheMoney(waitMoney);//// 待结算的金额
					userbook.setUserId(orders.getMuserId());
					userbook.setUpdatetime(new Date());
					userBookDao.updateUserBook(userbook);
					
					pushService.send(orders.getMuserId(), "账本变更","您的商城中已经成功交易一笔订单,收益金额:"+money+"请及时查看");
					/////////给商家保存一个消息
					UserMessage um=new UserMessage();
					um.setId(KeyGen.uuid());
					um.setDescribe("您的商城中已经成功交易一笔订单,收益金额:"+money+"请及时查看");
					um.setHeadStatus(0);
					um.setOrdersId(orders.getId());
					um.setTitle("账本变更");
					um.setTypes(4);
					um.setUserId(orders.getMuserId());
					messageDao.insert(um);
					
					
					

				}
				//////// 修改订单的状态~~~
				ordersDao.updateOrders(ordersId, ordersStatus, orders.getOrdersStatus(), new Date(), expressNo, null,
						deliveredtime, null, null);
				////// 修改订单产品的属性
				for (OrdersProduct ordersProduct : listop) {
					ordersProductDao.updateOrdersProduct(ordersProduct.getId(), ordersStatus, ordersName,
							ordersProduct.getStatusCode(), new Date());
				}
				result.setStatus("0");
				return result;
			}

			result.setStatus("1");
			result.setMessage("订单的类型不符");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新订单状态失败");
			return result;
		}

	}

	/**
	 * 扫码服务订单订单已确认
	 */
	@Override
	@Transactional
	public Results<String> updateServe(String statusCode, String ordersId, String userId) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		try {
			Orders orders = ordersDao.singleOrders(ordersId);
			if (orders.getOrdersType() == 2) {
				
				String statusName = "";
				///// 买家:CANCELSERVE 店铺商家操作的CLONSESERVE 已使用USESERVEE
				if("CANCELAPPLY".equals(statusCode)){
					if(!OrdersStatus.REJECTCANCELSERVEED.equals(orders.getOrdersStatus())){
						result.setStatus("1");
						result.setMessage("状态不符");
						return result;
					}
					statusCode=OrdersStatus.PAID;
					statusName="取消了服务订单的申请,服务订单回复原来的样子";
				}
				if ("CANCELSERVE".equals(statusCode)) {
					///// 买家取消服务的时候订单生成的码失效钱退回给买家
					if (!OrdersStatus.PAID.equals(orders.getOrdersStatus()) || !OrdersStatus.REJECTCANCELSERVEED.equals(orders.getOrdersStatus())) {
						result.setStatus("1");
						result.setMessage("状态不符");
						return result;
					}
					
						if ("CANCELSERVE".equals(statusCode)) {
							
							statusCode = OrdersStatus.CANCELSERVE;///// 退款成功的也就意味着取消预约工作已完成
							statusName = "成功取消预约";
						}
						pushService.send(orders.getMuserId(), "服务订单","您的销售订单中有一个用户申请了取消服务的,请及时处理~");
						/////////给商家保存一个消息
						UserMessage um=new UserMessage();
						um.setId(KeyGen.uuid());
						um.setDescribe("您的销售订单中有一个用户申请了取消服务的,请及时处理~");
						um.setHeadStatus(0);
						um.setOrdersId(orders.getId());
						um.setTitle("服务订单");
						um.setTypes(2);
						um.setSubType(2);
						um.setUserId(orders.getMuserId());
						messageDao.insert(um);
						
						/*if ("CLONSESERVE".equals(statusCode)) {
							OrdersPayment op = ordersDao.singlePayment(orders.getId());
							Map<String, String> map = new HashMap<String, String>();
							if ("ALIPAY".equals(op.getPaytypeCode())) {
								///////
								map = payService.aliOrdersRefund(op.getExtransno(),
										op.getMoney().setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
							} else if ("WXPAY".equals(op.getPaytypeCode())) {
								int freemoney = op.getMoney().multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN)
										.intValue();
								map = payService.wxOrderRefund(op.getExtransno(), freemoney, freemoney);
							}
							if(map.get("status")=="0"){
							///////待结算的金额减少  这个事件现在不会走
								
								
								
								
							statusCode = OrdersStatus.CLONSESERVE;///// 退款成功的也就意味着取消预约工作已完成
							statusName = "商家已经关闭了交易,钱已退回~";
						} else {
							result.setStatus("1");
							result.setMessage(map.get("error"));
							return result;
						}
						}*/

					
				}
				List<OrdersProduct> listop = ordersProductDao.selectByOid(orders.getId(), orders.getOrdersStatus());
				if ("USESERVEE".equals(statusCode)) {
					if (!OrdersStatus.PAID.equals(orders.getOrdersStatus()) ) {
						result.setStatus("1");
						result.setMessage("状态不符");
						return result;
					}
					statusCode = OrdersStatus.RECEIVED;
					statusName = "服务已经使用,订单完成";
					Results<User> userResult=new Results<User>();
					String userIds=null;
					if(userId!=null && !"".equals(userId)){
						userResult = userService.existUser(userId);
						if("1".equals(userResult.getStatus())){
							result.setStatus("1");
							result.setMessage(userResult.getMessage());
							return result;
						}
						userIds=userResult.getData().getId();
					}
					if (userId == null || "".equals(userId)) {
						result.setStatus("1");
						result.setMessage("登录用户的标识不能为空");
						return result;
					}
					if (!orders.getMuserId().equals(userIds)) {
						result.setStatus("1");
						result.setMessage("必须订单归属的商家扫码才可以");
						return result;
					}
					BigDecimal money = BigDecimal.ZERO;
					for (OrdersProduct ordersProduct : listop) {
						money = money.add(ordersProduct.getPayout());
					}
					///// 钱得到账本里
					UserIntomoneyHistory uh = new UserIntomoneyHistory();
					uh.setAddtime(new Date());
					uh.setIntoStatus(0);
					uh.setId(KeyGen.uuid());
					uh.setMoney(money);
					uh.setOrderId(ordersId);
					////// 往商家店铺里打钱~~~
					uh.setUserId(orders.getMuserId());
					userIntomoneyHistoryDao.saveUserIntomoneyHistory(uh);
					UserBook ub = userBookDao.singleUserBook(userIds);
					/// 修改总账本
					BigDecimal accountMoney = ub.getAccountMoney().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal canWithdraw = ub.getCanWithdraw().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal waitMoney = ub.getFortheMoney().subtract(money).setScale(2, BigDecimal.ROUND_DOWN);
					UserBook userbook = new UserBook();
					userbook.setAccountMoney(accountMoney);//// 总的金额
					userbook.setCanWithdraw(canWithdraw);/// 可提现金额
					userbook.setFortheMoney(waitMoney);//// 待结算的金额
					userbook.setUserId(orders.getMuserId());
					userbook.setUpdatetime(new Date());
					userBookDao.updateUserBook(userbook);
					
					
					pushService.send(orders.getMuserId(), "账本变更","您销售的服务项目"+listop.get(0).getProductName()+"已经使用,到账金额:"+money+"元");
					/////////给商家保存一个消息
					UserMessage um=new UserMessage();
					um.setId(KeyGen.uuid());
					um.setDescribe("您销售的服务项目"+listop.get(0).getProductName()+"已经使用,到账金额:"+money+"元");
					um.setHeadStatus(0);
					um.setOrdersId(orders.getId());
					um.setTitle("账本变更");
					um.setTypes(4);
					um.setUserId(orders.getMuserId());
					messageDao.insert(um);
					
					
				}
				

				//// 修改订单的状态
				ordersDao.updateOrders(orders.getId(), statusCode, orders.getOrdersStatus(), new Date(), null, null,
						null, null, null);
				////// 修改订单产品的属性
				for (OrdersProduct ordersProduct : listop) {
					ordersProductDao.updateOrdersProduct(ordersProduct.getId(), statusCode, statusName,
							ordersProduct.getStatusCode(), new Date());
				}
				result.setStatus("0");
				return result;

			}
			result.setStatus("1");
			result.setMessage("服务订单的类型不符");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("服务订单状态更新失败");
			return result;
		}

	}

	@Override
	public Results<OrdersVo> singleOrders(String ordersId) {
		Results<OrdersVo> result = new Results<OrdersVo>();
		OrdersVo od = new OrdersVo();
		Orders o = ordersDao.singleOrders(ordersId);

		BeanUtils.copyProperties(o, od);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (o.getPaidtime() != null) {
			od.setPaidtime(sf.format(o.getPaidtime()));
		}
		if (o.getDeliveredtime() != null) {
			od.setDeliveredtime(sf.format(o.getDeliveredtime()));
		}
		List<OrdersProduct> plist = ordersProductDao.selectByOid(ordersId, o.getOrdersStatus());
		BigDecimal totalprice = BigDecimal.ZERO;
		for (OrdersProduct op : plist) {
			totalprice = totalprice.add(op.getPayout());
			if(o.getOrdersType()==1){
				Course c=courseDao.singleCourse(op.getProductId());
				if(c!=null){
					op.setProductId(op.getProductId());
				}else{
					op.setClassId(op.getProductId());
				}
			}
			
		}
		////// 防止订单中有退款的产品
		od.setProduct(plist);
		od.setPayaccount(totalprice);
		result.setData(od);
		result.setStatus("0");
		return result;
	}

	@Override
	public Results<List<OrdersVo>> listOrders(OrdersDomain od) {
		// TODO Auto-generated method stub
		///// statusCode:AllStatus全部的订单
		// statusCode:refundStatus退款售后
		Results<List<OrdersVo>> result=new Results<List<OrdersVo>>();
		if(od.getUserId()!=null && !"".equals(od.getUserId())){
			Results<User> userResult = userService.existUser(od.getUserId());
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			od.setUserId(userResult.getData().getId());
		}
		if(od.getMuserId()!=null && !"".equals(od.getMuserId())){
			Results<User> userResult = userService.existUser(od.getMuserId());
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			od.setMuserId(userResult.getData().getId());
		}
		List<OrdersVo> odmainList = new ArrayList<OrdersVo>();
		if ("AllStatus".equals(od.getOrdersStatus())) {
			od.setOrdersStatus(null);
			List<Orders> orderList = ordersDao.ordersList(od);

			for (Orders orders : orderList) {
				OrdersVo ovo = new OrdersVo();
				BeanUtils.copyProperties(orders, ovo);
				if (orders.getPresetTime() != null) {

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					ovo.setPresetTime(sf.format(orders.getPresetTime()));
				}
				List<OrdersProduct> listOrders = ordersProductDao.selectByOid(orders.getId(), null);
				ovo.setProduct(listOrders);
				OrdersVo shopdomain = getShop(Integer.valueOf(od.getOrdersType()), orders.getMuserId(), orders.getAddtime());
				if (shopdomain != null) {
					ovo.setShopLogo(shopdomain.getShopLogo());
					ovo.setShopName(shopdomain.getShopName());
					ovo.setShowtime(shopdomain.getShowtime());
				}
				odmainList.add(ovo);
			}
			result.setStatus("0");
			result.setData(odmainList);
			return result;
		} else if ("refundStatus".equals(od.getOrdersStatus())) {
			////// 退款售后
			OrdersProductDomain opd = new OrdersProductDomain();
			opd.setMuserId(od.getMuserId());
			opd.setPageNo(od.getPageNo());
			opd.setPageSize(od.getPageSize());
			opd.setStatusCode(od.getOrdersStatus());
			opd.setUserId(od.getUserId());

			List<OrdersProduct> listOrders = ordersProductDao.listOrdersProduct(opd);
			for (OrdersProduct ordersProduct : listOrders) {
				OrdersVo ovo = new OrdersVo();
				List<OrdersProduct> listOrdersp = new ArrayList<OrdersProduct>();
				////// 查出商品所属的店铺的信息
				OrdersVo shopdomain = getShop(Integer.valueOf(od.getOrdersType()), ordersProduct.getMuserId(), null);
				if (shopdomain != null) {
					ovo.setShopLogo(shopdomain.getShopLogo());
					ovo.setShopName(shopdomain.getShopName());
				}
				listOrdersp.add(ordersProduct);
				ovo.setProduct(listOrdersp);
				odmainList.add(ovo);
			}
			result.setStatus("0");
			result.setData(odmainList);
			return result;

		}else if("serveOrdersIng".equals(od.getOrdersStatus())){
			//////服务订单的销售订单进行中包括申请取消的 拒绝取消的  未使用的  (如果是已完成的话传RECEIVED  如果是已关闭的话传CANCELSERVEED(取消成功))
			List<Orders> orderList = ordersDao.ordersList(od);

			for (Orders orders : orderList) {
				OrdersVo ovo = new OrdersVo();
				BeanUtils.copyProperties(orders, ovo);
				if (orders.getPresetTime() != null) {

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					ovo.setPresetTime(sf.format(orders.getPresetTime()));
				}
				List<OrdersProduct> listOrders = ordersProductDao.selectByOid(orders.getId(), null);
				ovo.setProduct(listOrders);
				OrdersVo shopdomain = getShop(Integer.valueOf(od.getOrdersType()), orders.getMuserId(), orders.getAddtime());
				if (shopdomain != null) {
					ovo.setShopLogo(shopdomain.getShopLogo());
					ovo.setShopName(shopdomain.getShopName());
					ovo.setShowtime(shopdomain.getShowtime());
				}
				odmainList.add(ovo);
			}
			result.setStatus("0");
			result.setData(odmainList);
			return result;
			
			
			
			
		} else {
			////商城 待付款(NEW) 待发货(PAID) 待收货(DELIVERED) 已完成(RECEIVED)   服务:(如果是已完成的话传RECEIVED  如果是已关闭的话传CANCELSERVEED(取消成功))
			List<Orders> orderList = ordersDao.ordersList(od);

			for (Orders orders : orderList) {
				OrdersVo ovo = new OrdersVo();
				////// 查出商品所属的店铺的信息
				List<OrdersProduct> listOrders = ordersProductDao.selectByOid(orders.getId(), od.getOrdersStatus());
				BigDecimal payaccount = BigDecimal.ZERO;
				int count = 0;
				BeanUtils.copyProperties(orders, ovo);
				if (orders.getPresetTime() != null) {

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					ovo.setPresetTime(sf.format(orders.getPresetTime()));
				}
				OrdersVo shopdomain = getShop(Integer.valueOf(od.getOrdersType()), orders.getMuserId(), orders.getAddtime());
				if (shopdomain != null) {
					ovo.setShopLogo(shopdomain.getShopLogo());
					ovo.setShopName(shopdomain.getShopName());
					ovo.setShowtime(shopdomain.getShowtime());
				}
				for (OrdersProduct ordersProduct : listOrders) {
					payaccount = payaccount.add(ordersProduct.getPayout());
					count += ordersProduct.getCount();
				}
				ovo.setPayaccount(payaccount);
				ovo.setCount(count);
				ovo.setProduct(listOrders);

				odmainList.add(ovo);
			}
			result.setStatus("0");
			result.setData(odmainList);
			return result;

		}

	}

	public OrdersVo getShop(int ordersType, String muserId, Date addtime) {
		int shopType = 0;
		if (ordersType == 0) {
			//// 药品的订单
			shopType = 2;
		} else if (ordersType == 1) {
			/// 课程的订单
			shopType = 0;
		} else if (ordersType == 2) {
			//// 服务店铺的订单
			shopType = 1;
		}
		OrdersVo od = new OrdersVo();
		if (shopType != 0) {
			Shop shop = shopDao.singleShop(muserId, null, 2,null);
			od.setShopLogo(shop.getShopLogo());
			od.setShopName(shop.getShopName());
		}
		if (addtime != null) {
			od.setShowtime(Patterns.sfDetailTime(addtime));
		}
		return od;

	}

	@Override
	public Results<OrdersCountVo> countOrders(String muserId, String userId) {
		// TODO Auto-generated method stub
		Results<OrdersCountVo> result = new Results<OrdersCountVo>();
		OrdersCountVo vo = new OrdersCountVo();
		String newUserId=null;
		if (muserId != null && (userId == null || "".equals(userId))) {
			//// 销售订单的个数
			Results<User> userResult = userService.existUser(muserId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			
			newUserId=userResult.getData().getId();
		}

		if (userId!=null && (muserId == null || "".equals(muserId))) {
			////// 这种情况下是指购物订单的个数
			Results<User> userResult = userService.existUser(userId);
			if("1".equals(userResult.getStatus())){
				result.setStatus("1");
				result.setMessage(userResult.getMessage());
				return result;
			}
			
			newUserId=userResult.getData().getId();

		}
		int newCount = ordersDao.ordersListCount(null, newUserId, OrdersStatus.NEW,null);
		int paidCount = ordersDao.ordersListCount(null, newUserId, OrdersStatus.PAID,"1");
		int wuseCount = ordersDao.ordersListCount(null, newUserId, OrdersStatus.PAID,"2");
		int deliveredCount = ordersDao.ordersListCount(null, newUserId, OrdersStatus.DELIVERED,null);
		//int refundCount = ordersProductDao.listOrdersProductCount(null, userId);
		
		vo.setDeliveredCount(deliveredCount);////待收货的个数
		vo.setPaidCount(paidCount);////待发货的个数
		vo.setNewCount(newCount);////代付款的个数
		vo.setWuseCount(wuseCount);////待使用的个数
		//vo.setRefundCount(refundCount);
		int favoriteCount = userFavoriteDao.favoriteCount(newUserId);
		UserBook ub = userBookDao.singleUserBook(newUserId);
		vo.setFavoriteCount(favoriteCount);//// 收藏的个数
		if(ub!=null){
			vo.setDoudouCount(Integer.valueOf(ub.getCanuseDoudou()));
		}
		
		result.setData(vo);
		result.setStatus("0");
		return result;

	}

}
