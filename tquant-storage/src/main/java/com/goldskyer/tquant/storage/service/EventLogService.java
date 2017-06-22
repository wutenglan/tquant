package com.goldskyer.tquant.storage.service;

import com.goldskyer.tquant.statistic.enums.Event;
import com.goldskyer.tquant.storage.entities.EventLog;

public interface EventLogService {
	public void addEventLog(EventLog eventLog);
	
	public void addEventLog(String sysCode,Event event);
}
