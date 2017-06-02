package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.Weekend;
import com.goldskyer.tquant.storage.enums.Exchange;


public interface WeekendService {

	public void load();
	public List<Weekend> queryAllWeekends();

	public String getPreTradingDateString(Exchange exchange, String dateString);

	public String getNextTradingDateString(Exchange exchange, String dateString);

	public String getNextTradingDateString(Exchange exchange, String dateString, Integer interval);

	public boolean isValidTradingDateString(Exchange exchange, String dateString);
}
