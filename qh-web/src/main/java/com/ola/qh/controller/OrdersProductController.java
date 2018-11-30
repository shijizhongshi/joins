package com.ola.qh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersProductRefund;
import com.ola.qh.service.IOrdersProductService;
import com.ola.qh.util.Results;

/**
 * 退款的申请  卖家审核订单的状态
 * 
 * @ClassName: OrdersProductController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author guoyuxue
 * @date 2018年11月30日
 *
 */
@RestController
@RequestMapping("/api/ordersProduct")
public class OrdersProductController {

	@Autowired
	private IOrdersProductService ordersproductService;

	@RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
	public Results<String> applyRefundOrders(@RequestBody @Valid OrdersProductRefund or, BindingResult valid) {
		Results<String> result = new Results<String>();
		if (valid.hasErrors()) {
			result.setStatus("1");
			result.setMessage("申请退款信息填写不完整,请检查");
			return result;
		}
		return ordersproductService.applyRefund(or);
	}
	/**
	 * 拒绝退款申请    同意退款申请   取消退款申请
	 * <p>Title: updateRefundOrders</p>  
	 * <p>Description: </p>  
	 * @param ordersProductId
	 * @param statusCode
	 * @return
	 */
	@RequestMapping("/updateRefund")
	public Results<String> updateRefundOrders(
			@RequestParam(name="ordersProductId",required=true)String ordersProductId,
			@RequestParam(name="statusCode",required=true)String statusCode){
		
		
		return ordersproductService.updateRefund(ordersProductId, statusCode);
	}
	/**
	 * 修改退款的申请
	 * <p>Title: alterRefund</p>  
	 * <p>Description: </p>  
	 * @param opr
	 * @return
	 */
	@RequestMapping(value="/alterRefund",method=RequestMethod.POST)
	public Results<String> alterRefund(@RequestBody OrdersProductRefund opr){
		Results<String> result=new Results<String>();
		if(opr.getId()==null || "".equals(opr.getId())){
			result.setStatus("1");
			result.setMessage("标识不能为空");
			return result;
		}
		
		return ordersproductService.applyRefund(opr);
		
	}
	/**
	 * 退款订单的详情页的查询
	 * <p>Title: singleOrdersProduct</p>  
	 * <p>Description: </p>  
	 * @param ordersProductId
	 * @return
	 */
	@RequestMapping(value="/single",method=RequestMethod.GET)
	public Results<OrdersProduct> singleOrdersProduct(@RequestParam(name="ordersProductId")String ordersProductId){
		return ordersproductService.single(ordersProductId);
	}
}
