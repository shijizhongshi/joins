package com.ola.qh.controller;

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
import com.ola.qh.entity.OrdersStatus;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.Results;
/**
 * 订单的提交  订单的查询 订单的修改
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
	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public Results<Map<String,String>> submitOrders(@RequestBody @Valid OrdersVo ordersVo,BindingResult valid,HttpServletRequest request){
		Results<Map<String,String>> result = new Results<Map<String,String>>();
		if(ordersVo.getOrdersType()==0){
			///////说明是药品的购买~~~
			if(ordersVo.getAddress()==null || "".equals(ordersVo.getAddress())){
				result.setStatus("1");
				result.setMessage("买家地址不能为空");
				return result;
			}
			if(ordersVo.getReceiver()==null || "".equals(ordersVo.getReceiver())){
				result.setStatus("1");
				result.setMessage("买家收货人不能为空");
				return result;
			}
			if(ordersVo.getMobile()==null || "".equals(ordersVo.getMobile())){
				result.setStatus("1");
				result.setMessage("买家手机号不能为空");
				return result;
			}
		}
		if(valid.hasErrors()){
			//////信息不完整
			result.setStatus("1");
			result.setMessage("订单信息提交不完整");
			return result;
		}
		Results<List<OrdersPayment>> results = orderService.submitOrders(ordersVo);
		if("0".equals(results.getStatus())){
			if(!"ALIPAY".equals(ordersVo.getPaytypeCode()) && !"WXPAY".equals(ordersVo.getPaytypeCode())){
				result.setStatus("1");
				result.setMessage("只支持支付宝或者微信支付");
				return result;
			}
			if("ALIPAY".equals(ordersVo.getPaytypeCode())){
				//////调用支付宝支付的接口
				Results<String> aliResult = payService.aliprepay(results.getData());
				result.setStatus(aliResult.getStatus());
				if("0".equals(aliResult.getStatus())){
					Map<String,String> map = new HashMap<String,String>();
					map.put("alibody", aliResult.getData());
					result.setData(map);
					return result;
				}else{
					result.setMessage(aliResult.getMessage());
					return result;
				}
			}
			if("WXPAY".equals(ordersVo.getPaytypeCode())){
				//////调用微信支付的接口
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
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Results<String> updateOrders(@RequestParam(name="statusCode",required=true)String statusCode,
			@RequestParam(name="statusName",required=true)String statusName,
			@RequestParam(name="expressNo",required=false)String expressNo,
			@RequestParam(name="ordersId",required=true)String ordersId){
			
		Results<String> result = new Results<String>();
		if(OrdersStatus.DELIVERED.equals(statusCode)){
			//////发货
			if(expressNo==null || "".equals(expressNo)){
				result.setStatus("1");
				result.setMessage("快递单号不能为空");
				return result;
			}
		}
		
		return null;
		
	}
}
