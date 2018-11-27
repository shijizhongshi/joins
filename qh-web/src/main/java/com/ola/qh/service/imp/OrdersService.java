package com.ola.qh.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersVo;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.util.Results;
@Service
public class OrdersService implements IOrdersService{

	@Override
	@Transactional
	public Results<String> submitOrders(OrdersVo ordersVo) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		try {
			
			
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setStatus("1");
			result.setMessage("提交信息处理有误~");
			return result;
		}
	}

}
