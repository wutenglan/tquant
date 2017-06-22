package com.goldskyer.tquant.storage.monitor.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.storage.monitor.TickMonitor;
import com.goldskyer.tquant.storage.monitor.vo.TickIndex;
import com.goldskyer.tquant.storage.service.EventLogService;

public class ColseMarketMonitor extends BaseMonitor implements TickMonitor{
	private static final Log log=LogFactory.getLog(ColseMarketMonitor.class);
	@Autowired
	private EventLogService eventLogService;
	
	@Override
	public void monitor(int tickId) {
		if(tickId!=TickIndex.MAX_TICK_ID)
		{
			return;
		}
		log.info("进行收盘监控");
		
	}	

}
