package com.ola.qh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Bytes;
import com.ola.qh.util.KeyGen;
@RestController
public class QrcodeController {
	
	@Autowired
	private IStoreService storeService;
	
	@RequestMapping("/api/url")
	 public void seeUrl() throws Exception{
		String content="小朋友你好呀~";
		byte[] bytes = Bytes.qrcode(content, 300, 300);
		String fname = KeyGen.gen() + ".jpg";
		String url  = storeService.storeUrl(fname, bytes);
		System.out.println(url);
	 }

}
