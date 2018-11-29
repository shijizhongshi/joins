package com.ola.qh.service;

import java.util.List;

import com.ola.qh.entity.OrdersPayment;

public interface IPayResultService {

	public void payDrugResults(List<OrdersPayment> oplist);
	
	public void payCourseResults(List<OrdersPayment> oplist);
}
