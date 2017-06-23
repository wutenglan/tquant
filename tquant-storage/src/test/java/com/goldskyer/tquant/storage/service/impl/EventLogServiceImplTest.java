package com.goldskyer.tquant.storage.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.statistic.enums.Event;
import com.goldskyer.tquant.storage.service.EventLogService;

public class EventLogServiceImplTest extends BaseTest{
	
	@Autowired
	private EventLogService eventLogService;
	@Test
	@Rollback(true)
	public void testAdd()
	{
		eventLogService.addEventLog("0001", Event.一字涨停);	
	}
}
