package com.ola.qh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.service.ISendSmsService;

@RestController
public class SendSmsController {

	@Autowired
	private ISendSmsService sendSmsService;
	
}
