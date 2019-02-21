package com.ola.qh.service.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ola.qh.dao.OrdersDao;
import com.ola.qh.entity.BizContent;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.PayPipeline;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.MD5;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.RBuilder;
import com.ola.qh.util.Results;
import com.ola.qh.vo.WXPrePayResult;

/**
 * 调用支付宝和微信的接口
* @ClassName: PayService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月28日  
*
 */
@Service
public class PayService implements IPayService {

	@Autowired
	private OrdersDao ordersDao;

	/**
	 * 调用支付宝的接口
	 */
	@Override
	public Results<String> aliprepay(List<OrdersPayment> pays) {
		// TODO Auto-generated method stub
		
		///// 查询回调地址~~~~
		Results<String> result = new Results<String>();
		PayPipeline pp = ordersDao.singlePayPipeline("ALIPAY");
		BigDecimal money = BigDecimal.ZERO;
		for (OrdersPayment op : pays) {
			money = money.add(op.getMoney()).setScale(2, BigDecimal.ROUND_DOWN);
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
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response;
			try {
				response = alipayclient().sdkExecute(request);
				System.out.println(response.getBody());
				/*if("10000".equals(response.getCode())){
					result.setData(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
					result.setStatus("0");
					return result;
				}*/
				if(response.isSuccess()){
					System.out.println("调用成功");
					result.setData(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
					result.setStatus("0");
					return result;
					} else {
					System.out.println("调用失败");
					result.setStatus("1");
					result.setMessage(response.getMsg());
					return result;
					}
				
			} catch (AlipayApiException e) {
				result.setStatus("1");
				result.setMessage("调用支付宝接口失败~");
				return null;
			}
		
	}

	public static DefaultAlipayClient alipayclient() {

		DefaultAlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Patterns.ALI_APPID, Patterns.ALIPAY_PRIVATE_KEY,
				"json", "utf-8",
				// 支付宝公钥 (ALIPAY_PUBLIC_KEY)，RSA支付宝公钥固定的，推荐使用rsa2的，这里先使用的rsa测试
				Patterns.ALIPAY_PUBLIC_KEY, "RSA2");
		return alipayClient;
	}
	
	/**
	 * 调用微信的接口
	 * @throws IOException 
	 * @throws UnsupportedOperationException 
	 */
	@Override
	public Results<Map<String, String>> wxprepay(List<OrdersPayment> pays,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Results<Map<String, String>> result=new Results<Map<String, String>>();
		
		
		PayPipeline pp = ordersDao.singlePayPipeline("WXPAY");

		String ip;

		if (request.getHeader("X-Real-IP") != null)
		{
		    ip = request.getHeader("X-Real-IP");
		}
		ip = request.getRemoteAddr();

		BigDecimal money=BigDecimal.ZERO;
		for (OrdersPayment ordersPayment : pays)
		{
			money = money.add(ordersPayment.getMoney()).setScale(2, BigDecimal.ROUND_DOWN);
		}
		BigDecimal summoney = money.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("appid", Patterns.wxappId);// 公众账号
		requestParams.put("mch_id", Patterns.wxmchId);// 商户号
		requestParams.put("nonce_str", new String(new RBuilder().length(16).hasletter(true).next()));// 随机字符串，不长于32位
		requestParams.put("body", pays.get(0).getBodyDetail());
		requestParams.put("out_trade_no", pays.get(0).getExtransno());
		requestParams.put("fee_type", "CNY");// 人民币
		/*requestParams.put("total_fee", String.valueOf(summoney)); /// 单位分 */
		requestParams.put("total_fee", "1");
		requestParams.put("spbill_create_ip", ip);// APP和网页支付提交用户端ip
		requestParams.put("notify_url", pp.getNoticeUrl());// 接收微信支付异步通知回调地址
		requestParams.put("trade_type", "APP");// 交易类型
		/* String signkey = payPipeline.getSignkey(); */
		String signkey = Patterns.wxsignkey;//////私钥
		requestParams.put("sign", MD5.digest(compositeWXPayKeyValuePaires(requestParams, signkey)).toUpperCase());
		HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");

		StringBuilder xml = creatPostEntity(requestParams);
		post.setEntity(new StringEntity(xml.toString(), "utf-8"));

		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		if (Objects.isNull(entity))
		{
		    requestParams.clear();
		    result.setStatus("1");
		    result.setMessage("支付信息有误，请稍后重试！");
		    return result;
		}
		InputStream is = entity.getContent();
		    JAXBContext jaxbcontext = JAXBContext.newInstance(WXPrePayResult.class);
		    WXPrePayResult wxresult = (WXPrePayResult) jaxbcontext.createUnmarshaller().unmarshal(is);
		    if (StringUtils.hasText(wxresult.getReturn_code()) && StringUtils.hasText(wxresult.getResult_code()) && wxresult.getResult_code().equals("SUCCESS") && wxresult
			    .getReturn_code().equals("SUCCESS"))
		    {
		    /////将wxresult这个对象的值转换成键值对的样子	
			Map<String, String> mapresult = BeanUtils.describe(wxresult);
			result.setStatus("0");
			result.setData(mapresult);
			return result;
			
		    }
		    //Map<String, String> result = new HashMap<String, String>();
		    result.setMessage("支付信息有误，请稍后重试！");
		    result.setStatus("1");
		    return result;
		
	}
	
	 private StringBuilder creatPostEntity(Map<String, String> result)
	    {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>\n");
		result.entrySet().stream().sorted((v1, v2) -> v1.getKey().compareTo(v2.getKey())).forEach(entry -> {
		    if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey()))
		    {
			xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
		    }
		    else
		    {
			xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
		    }
		});
		xml.append("</xml>");
		return xml;
	    }
	 
	 private String compositeWXPayKeyValuePaires(Map<String, String> result, String key)
	    {
		StringBuilder builder = new StringBuilder();
		result.entrySet().stream().filter(x -> !x.getKey().equals("sign") && !x.getKey().equals("class")).sorted((x, y) -> x.getKey().compareTo(y.getKey())).forEach((x) -> {
		    builder.append(x.getKey()).append("=").append(x.getValue()).append("&");
		});
		builder.append("key=").append(key);
		return builder.toString();
	    }

	@Override
	public Map<String, String> aliOrdersRefund(String extranceno,double money) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		BizContent bigContent = new BizContent();
		bigContent.setOut_trade_no(extranceno);
		bigContent.setOut_request_no(KeyGen.uuid()+"");
		
		bigContent.setRefund_amount(0.01);
		//bigContent.setRefund_amount(money);
		bigContent.setRefund_reason("正常退款");
		String biz_content=Json.to(bigContent);
		request.setBizContent(biz_content);
		
		AlipayTradeRefundResponse response = alipayclient().execute(request);
		if(response.isSuccess()){
		    map.put("status", "0");
		} else {
		    map.put("status", "1");
		    map.put("error", response.getSubMsg());
		}
		return map;
	}
	 
	 
	
	
	 
    @Override
    public Map<String, String> wxOrderRefund(String extranceno,int totalfee,int refundfee) throws Exception
    {
	
	Map<String, String> message = new HashMap<String, String>();
	KeyStore keyStore = KeyStore.getInstance("PKCS12");
	FileInputStream instream = new FileInputStream(new File(Patterns.cert_path));
/*	FileInputStream instream = new FileInputStream(new File("/common/apiclient_cert.p12"));*/
	try
	{
	    keyStore.load(instream, Patterns.wxmchId.toCharArray());
	}
	finally
	{
	    instream.close();
	}

	// Trust own CA and all self-signed certs
	SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Patterns.wxmchId.toCharArray()).build();
	// Allow TLSv1 protocol only
	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]
	{
		"TLSv1"
	}, null, new DefaultHostnameVerifier());
	CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	try
	{
	    Map<String, String> requestParams = new HashMap<String, String>();
	    requestParams.put("appid", Patterns.wxappId);// 公众账号ID
	    requestParams.put("mch_id", Patterns.wxmchId);// 商户号
	    requestParams.put("nonce_str", new String(new RBuilder().length(16).hasletter(true).next()));// 随机字符串，不长于32位
	    requestParams.put("out_trade_no", extranceno);// 商户系统内部的订单号
	    requestParams.put("out_refund_no", KeyGen.uuid() + "");
	    requestParams.put("total_fee", "" + 1);// 单位分
	    requestParams.put("refund_fee", "" + 1);// APP和网页支付提交用户端ip
	    
	   /* requestParams.put("total_fee", "" + totalfee);// 单位分
	    requestParams.put("refund_fee", "" + refundfee);// APP和网页支付提交用户端ip
*/	    requestParams.put("op_user_id", Patterns.wxmchId);// 交易类型

	    String signkey = Patterns.wxsignkey;/////秘钥
	    requestParams.put("sign", MD5.digest(compositeWXPayKeyValuePaires(requestParams, signkey)).toUpperCase());

	    HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
	    StringBuilder xml = creatPostEntity(requestParams);
	    httppost.setEntity(new StringEntity(xml.toString(), "utf-8"));
	    System.out.println("executing request" + httppost.getRequestLine());
	    CloseableHttpResponse response = httpclient.execute(httppost);
	    try
	    {
		HttpEntity entity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (entity != null)
		{
		    System.out.println("Response content length: " + entity.getContentLength());
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String text;
		    
		    SAXReader saxReader = new SAXReader();

		    org.dom4j.Document document = saxReader.read(entity.getContent());
		    org.dom4j.Element root = document.getRootElement();
		    // 获取所有子元素
		    List<org.dom4j.Element> childList = root.elements();
		   
		    // 迭代输出
		    for (Iterator<org.dom4j.Element> iter = root.elementIterator(); iter.hasNext();)
		    {
			org.dom4j.Element e = (org.dom4j.Element) iter.next();
			message.put(e.getName(), e.getText());
		    }
		    
		   if("SUCCESS".equals(message.get("result_code"))){
		       message.put("status", "0");
		       
		   }else{
		       message.put("status", "1");
		       message.put("error", message.get("err_code_des"));
		   }
		    
		}
		EntityUtils.consume(entity);
	    }
	    finally
	    {
		response.close();
	    }
	}
	finally
	{
	    httpclient.close();
	}
	return message;
    }
}