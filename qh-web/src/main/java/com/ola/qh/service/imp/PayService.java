package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ola.qh.dao.OrdersDao;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayPipeline;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.Results;

@Service
public class PayService implements IPayService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public Results<String> aliprepay(List<OrdersPayment> pays) {
		// TODO Auto-generated method stub
		///// 查询回调地址~~~~
		Results<String> result = new Results<String>();
		PayPipeline pp = ordersDao.singlePayPipeline("ALIPAY");
		BigDecimal money = BigDecimal.ZERO;
		for (OrdersPayment op : pays) {
			money.add(op.getMoney());
		}

		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

		model.setOutTradeNo(pays.get(0).getExtransno());
		model.setTimeoutExpress("30m");
		/* model.setTotalAmount(String.valueOf(money)); */
		model.setSubject(pays.get(0).getSubjectTitle());
		model.setBody(pays.get(0).getBodyDetail());
		model.setTotalAmount("0.01");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(pp.getNoticeUrl());
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayclient().sdkExecute(request);
			System.out.println(response.getBody());
			/*
			 * map.put("alibody", response.getBody()); map.put("money",
			 * String.valueOf(money)); map.put("error", "0");
			 */
			// return map;//就是orderString 可以直接给客户端请求，无需再做处理。
		} catch (AlipayApiException e) {
			/* map.put("error", "1"); */
		}
		return null;
	}

	public static DefaultAlipayClient alipayclient() {

		DefaultAlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "", "",
				"json", "utf-8",
				// 支付宝公钥 (ALIPAY_PUBLIC_KEY)，RSA支付宝公钥固定的，推荐使用rsa2的，这里先使用的rsa测试
				"", "RSA2");
		return alipayClient;
	}
}