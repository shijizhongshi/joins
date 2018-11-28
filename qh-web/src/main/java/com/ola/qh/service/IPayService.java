package com.ola.qh.service;

import java.util.List;
import java.util.Map;

import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.util.Results;

public interface IPayService {

	public Results<String> aliprepay(List<OrdersPayment> pays);
}
