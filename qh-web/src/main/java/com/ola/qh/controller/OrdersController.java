package com.ola.qh.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersVo;
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

	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public Results<String> submitOrders(@RequestBody @Valid OrdersVo ordersVo,BindingResult valid){
		Results<String> result = new Results<String>();
		if(ordersVo.getOrdersTypes()==0){
			///////说明是药品的购买~~~
			if(ordersVo.getAddress()==null || "".equals(ordersVo.getAddress())){
				result.setStatus("1");
				result.setMessage("买家地址不能为空");
				return result;
			}
			if(ordersVo.getAddress()==null || "".equals(ordersVo.getAddress())){
				result.setStatus("1");
				result.setMessage("买家地址不能为空");
				return result;
			}
			if(ordersVo.getAddress()==null || "".equals(ordersVo.getAddress())){
				result.setStatus("1");
				result.setMessage("买家地址不能为空");
				return result;
			}
		}
		if(valid.hasErrors()){
			//////信息不完整
			result.setStatus("1");
			result.setMessage("订单信息提交不完整");
			return result;
		}
		
		return null;
		
	}
	
	
}
