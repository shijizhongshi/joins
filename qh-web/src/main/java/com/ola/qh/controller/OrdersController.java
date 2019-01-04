package com.ola.qh.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.vo.OrdersCartDomain;
import com.ola.qh.vo.OrdersCountVo;
import com.ola.qh.vo.OrdersDomain;
import com.ola.qh.vo.OrdersVo;
import com.ola.qh.vo.ProductBuyDomain;

/**
 * 订单的提交 订单的查询 订单的修改
 * 
 * @ClassName: OrdersController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guoyuxue
 * @date 2018年11月27日
 *
 */
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private IOrdersService orderService;
	@Autowired
	private IPayService payService;

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Results<Map<String, String>> submitOrders(@RequestBody @Valid OrdersCartDomain ordersVo, BindingResult valid,
			HttpServletRequest request) {
		Results<Map<String, String>> result = new Results<Map<String, String>>();
		if (ordersVo.getOid() == null || "".equals(ordersVo.getOid())) {
			if (ordersVo.getOrdersType() == 0) {
				/////// 说明是药品的购买~~~
				if (ordersVo.getAddress() == null || "".equals(ordersVo.getAddress())) {
					result.setStatus("1");
					result.setMessage("买家地址不能为空");
					return result;
				}
				if (ordersVo.getReceiver() == null || "".equals(ordersVo.getReceiver())) {
					result.setStatus("1");
					result.setMessage("买家收货人不能为空");
					return result;
				}
				if (ordersVo.getMobile() == null || "".equals(ordersVo.getMobile())) {
					result.setStatus("1");
					result.setMessage("买家手机号不能为空");
					return result;
				}
			}
			if (valid.hasErrors()) {
				////// 信息不完整
				result.setStatus("1");
				result.setMessage("订单信息提交不完整");
				return result;
			}
		}
		Results<List<OrdersPayment>> results = orderService.submitOrders(ordersVo);

		if ("0".equals(results.getStatus())) {
			if (!"ALIPAY".equals(ordersVo.getPaytypeCode()) && !"WXPAY".equals(ordersVo.getPaytypeCode())) {
				result.setStatus("1");
				result.setMessage("只支持支付宝或者微信支付");
				return result;
			}
			if ("ALIPAY".equals(ordersVo.getPaytypeCode())) {
				////// 调用支付宝支付的接口
				Results<String> aliResult = payService.aliprepay(results.getData());
				result.setStatus(aliResult.getStatus());
				if ("0".equals(aliResult.getStatus())) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("alibody", aliResult.getData());
					result.setData(map);
					return result;
				} else {
					result.setMessage(aliResult.getMessage());
					return result;
				}
			}
			if ("WXPAY".equals(ordersVo.getPaytypeCode())) {
				////// 调用微信支付的接口
				try {
					result = payService.wxprepay(results.getData(), request);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.setStatus("1");
					result.setMessage("处理异常呢~");
					return result;
				}
			}

		}
		result.setStatus(results.getStatus());
		result.setMessage(results.getMessage());
		return result;

	}

	@RequestMapping(value = "/submitSingle", method = RequestMethod.POST)
	public Results<Map<String, String>> submitSingle(@RequestBody @Valid ProductBuyDomain productVo,
			BindingResult valid, HttpServletRequest request) throws Exception {
		Results<Map<String, String>> result = new Results<Map<String, String>>();
		OrdersCartDomain ordersVo = new OrdersCartDomain();

		List<Orders> olist = new ArrayList<Orders>();
		Orders o = new Orders();
		o.setMuserId(productVo.getMuserId());
		o.setLeaveMessage(productVo.getLeaveMessage());
		o.setSex(productVo.getSex());
		/////// 预定或者是购买
		o.setPaymentType(productVo.getPaymentType());
		if (productVo.getPresetTime() != null && !"".equals(productVo.getPresetTime())) {
			///// 预约的时间(如果不是预定购买的话就没有这个字段)
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			o.setPresetTime(sf.parse(productVo.getPresetTime()));
		}

		List<OrdersProduct> oplist = new ArrayList<OrdersProduct>();
		OrdersProduct op = new OrdersProduct();
		op.setProductId(productVo.getProductId());///// 产品的id
		op.setCount(productVo.getCount());
		oplist.add(op);
		o.setProduct(oplist);
		olist.add(o);
		ordersVo.setOrdersList(olist);
		ordersVo.setOrdersType(productVo.getOrdersType());
		ordersVo.setPaytypeCode(productVo.getPayTypeCode());
		ordersVo.setPaytypeName(productVo.getPayTypeName());
		ordersVo.setTotalPayout(productVo.getTotalPayout());
		ordersVo.setUserId(productVo.getUserId());
		ordersVo.setMobile(productVo.getMobile());
		ordersVo.setReceiver(productVo.getReceiver());

		Results<List<OrdersPayment>> results = orderService.submitOrders(ordersVo);

		if ("0".equals(results.getStatus())) {
			if (!"ALIPAY".equals(ordersVo.getPaytypeCode()) && !"WXPAY".equals(ordersVo.getPaytypeCode())) {
				result.setStatus("1");
				result.setMessage("只支持支付宝或者微信支付");
				return result;
			}
			if ("ALIPAY".equals(ordersVo.getPaytypeCode())) {
				////// 调用支付宝支付的接口
				Results<String> aliResult = payService.aliprepay(results.getData());
				result.setStatus(aliResult.getStatus());
				if ("0".equals(aliResult.getStatus())) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("alibody", aliResult.getData());
					result.setData(map);
					return result;
				} else {
					result.setMessage(aliResult.getMessage());
					return result;
				}
			}
			if ("WXPAY".equals(ordersVo.getPaytypeCode())) {
				////// 调用微信支付的接口
				try {
					result = payService.wxprepay(results.getData(), request);
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.setStatus("1");
					result.setMessage("处理异常呢~");
					return result;
				}
			}

		}
		result.setStatus(results.getStatus());
		result.setMessage(results.getMessage());
		return result;

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Results<String> updateOrders(@RequestParam(name = "statusCode", required = true) String statusCode,
			@RequestParam(name = "statusName", required = false) String statusName,
			@RequestParam(name = "expressNo", required = false) String expressNo,
			@RequestParam(name = "ordersId", required = true) String ordersId) {

		Results<String> result = new Results<String>();
		if (OrdersStatus.DELIVERED.equals(statusCode)) {
			////// 发货
			if (expressNo == null || "".equals(expressNo)) {
				result.setStatus("1");
				result.setMessage("快递单号不能为空");
				return result;
			}
		}

		return orderService.updateOrders(statusCode, statusName, expressNo, ordersId);

	}

	@RequestMapping("/updateServe")
	public Results<String> updateServe(@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "ordersId", required = true) String ordersId,
			@RequestParam(name = "statusCode", required = true) String statusCode) {

		return orderService.updateServe(statusCode, ordersId, userId);
	}

	/**
	 * 正常订单的订单详情
	 * <p>
	 * Title: singleOrders
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param ordersId
	 * @return
	 */
	@RequestMapping("/single")
	public Results<OrdersVo> singleOrders(@RequestParam(name = "ordersId", required = true) String ordersId) {

		return orderService.singleOrders(ordersId);
	}

	@RequestMapping("/list")
	public Results<List<OrdersVo>> listOrders(
			@RequestParam(name = "statusCode", required = true) String statusCode,
			@RequestParam(name = "page", required = true) int page,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "muserId", required = false) String muserId,
			@RequestParam(name = "ordersType", required = false) int ordersType) {

		Results<List<OrdersVo>> result = new Results<List<OrdersVo>>();
		int pageSize = Patterns.zupageSize;
		int pageNo = (page - 1) * pageSize;
		OrdersDomain od = new OrdersDomain();
		od.setPageNo(pageNo);
		od.setPageSize(pageSize);
		od.setOrdersType(ordersType);
		od.setUserId(userId);
		od.setMuserId(muserId);
		od.setOrdersStatus(statusCode);
		List<OrdersVo> listDomain = orderService.listOrders(od);
		result.setStatus("0");
		result.setData(listDomain);
		return result;
	}

	@RequestMapping("/count")
	public Results<OrdersCountVo> countOrders(
			@RequestParam(name = "muserId", required = true) String muserId,
			@RequestParam(name = "userId", required = true) String userId) {
		return orderService.countOrders(muserId,userId);
	}
}
