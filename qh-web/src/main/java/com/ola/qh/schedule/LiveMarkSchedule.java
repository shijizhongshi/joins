package com.ola.qh.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ola.qh.service.ICourseService;

@Component
public class LiveMarkSchedule {

	@Autowired
	private ICourseService courseService;

	public void liveMark() {
		// 定时发送直播开始的提醒消息
		courseService.timedPushOneHour();
	}

}
