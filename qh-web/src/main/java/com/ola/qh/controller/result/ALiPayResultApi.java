package com.ola.qh.controller.result;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.dao.OrdersDao;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayResult;
import com.ola.qh.util.KeyGen;

@RestController
public class ALiPayResultApi {

	@Autowired
	private OrdersDao ordersDao;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public void aliPayResult(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();

		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();)
		{
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++)
		    {
			valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
		    }
		    // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		    // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
		    params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//////////////////////////////////////////////////////////////////////////////////////////
		// 请在这里加上商户的业务逻辑程序代码
		
		 /* String out_trade_no ="4457782249982537401"; 
		  String trade_status = "TRADE_SUCCESS";*/
		 
		// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

		if (trade_status.equals("TRADE_FINISHED"))
		{
		    // 判断该笔订单是否在商户网站中已经做过处理
		    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		    // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
		    // 如果有做过处理，不执行商户的业务程序
		    // 注意：
		    // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
		}
		else if (trade_status.equals("TRADE_SUCCESS"))
		{

		    // 判断该笔订单是否在商户网站中已经做过处理

		    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		    // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
		    // 如果有做过处理，不执行商户的业务程序
		    // 查出对应的支付信息===============================

		    List<OrdersPayment> ordersPayment = ordersDao.listByExtransno(out_trade_no);
		    if (ordersPayment != null && ordersPayment.get(0).getDone() != 1)
		    {
		    	PayResult pr = new PayResult();
		    	pr.setId(KeyGen.uuid());
		    	pr.setAddtime(new Date());
		    	pr.setComment("付款成功,进入回调之后的方法");
		    	pr.setExtransno(ordersPayment.get(0).getExtransno());
		    	pr.setPayStatus("success");
		    	pr.setUserId(ordersPayment.get(0).getUserId());
		    	ordersDao.insertPayResult(pr);
		    	////////回调的方法
		    	
		    }

		    // 注意：
		    // 付款完成后，支付宝系统发送该交易状态通知
		}
		// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("success");
		//return null;
		
	}
}
