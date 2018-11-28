package com.ola.qh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.service.IOrdersService;
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
	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public Results<String> submitOrders(@RequestBody @Valid OrdersVo ordersVo,BindingResult valid){
		Results<String> result = new Results<String>();
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
			if(ordersVo.getMobile()!=null || "".equals(ordersVo.getMobile())){
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
			if("ALIPAY".equals(ordersVo.getPaytypeCode())){
				//////调用支付宝支付的接口
				
				
			}
			if("WXPAY".equals(ordersVo.getPaytypeCode())){
				//////调用微信支付的接口
				
			}
			
		}
		return null;
		
	}
	
	
}
