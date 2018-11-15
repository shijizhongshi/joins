package com.ola.qh.service;

import java.util.Map;

import com.ola.qh.util.Results;

public interface ISendSmsService {

	public Results<String> sendSms(String mobile, String templateCode, Map<String, String> map);
}
