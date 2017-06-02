package com.goldskyer.tquant.storage.task;

import java.util.Date;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.service.WeekendService;
import com.goldskyer.tquant.storage.utils.SpringContextHolder;

public abstract class BaseScheduleTask extends AbstractScheduleTask
{
	

	@Override
	public boolean isValidTime()
	{
		return false;
	}

	@Override
	public boolean isValidDay()
	{
		WeekendService weekendService = SpringContextHolder.getBean(WeekendService.class);
		return weekendService.isValidTradingDateString(Exchange.XSHG, DateUtil.date2String(new Date()));
	}

}
