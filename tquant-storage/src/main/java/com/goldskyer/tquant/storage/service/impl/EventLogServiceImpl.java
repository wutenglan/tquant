package com.goldskyer.tquant.storage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.statistic.enums.Event;
import com.goldskyer.tquant.storage.entities.EventLog;
import com.goldskyer.tquant.storage.service.EventLogService;

@Service
public class EventLogServiceImpl extends BaseServiceImpl implements EventLogService{

	@Override
	public void addEventLog(EventLog eventLog) {
		baseDao.add(eventLog);
	}
	
	public void addEventLog(String sysCode,Event event) {
		EventLog eventLog=new EventLog();
		eventLog.setCreateTime(new Date());
		eventLog.setEventName(event.name());
		eventLog.setSysCode(sysCode);
		baseDao.add(eventLog);
	}
	
}
