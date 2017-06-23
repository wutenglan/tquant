package com.goldskyer.tquant.storage.monitor.impl;

import java.math.BigDecimal;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.statistic.enums.Event;
import com.goldskyer.tquant.storage.business.DayDataFrameBusiness;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.DayDataFrame;
import com.goldskyer.tquant.storage.entities.FullDataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.monitor.TickMonitor;
import com.goldskyer.tquant.storage.monitor.vo.TickIndex;
import com.goldskyer.tquant.storage.service.DayDataFrameService;
import com.goldskyer.tquant.storage.service.EventLogService;
import com.goldskyer.tquant.storage.service.StorageFileService;
import com.goldskyer.tquant.storage.service.WeekendService;
import com.goldskyer.tquant.storage.utils.FullDataFrameUtil;

public class CloseMarketMonitor extends BaseMonitor implements TickMonitor {
	private static final Log log = LogFactory.getLog(CloseMarketMonitor.class);
	@Autowired
	private EventLogService eventLogService;

	@Autowired
	private DayDataFrameService dayDataFrameService;

	@Override
	public void monitor(int tickId) {
		if (tickId != TickIndex.MAX_TICK_ID) {
			return;
		}
		log.info("进行收盘监控");
		String dateString = DateUtil.formatDate(new Date(), DateUtil.FMT_DATE_SPECIAL);
		List<DayDataFrame> days = dayDataFrameService.querySurgeLimit(Exchange.XSHG, dateString);
		for (DayDataFrame day : days) {
			eventLogService.addEventLog(day.getSysCode(), Event.当日涨停);
		}

		List<DayDataFrame> continus = dayDataFrameService.querySurgeLimitContinous(Exchange.XSHG, dateString,
				new BigDecimal("0.08"));
		for (DayDataFrame day : continus) {
			eventLogService.addEventLog(day.getSysCode(), Event.连续涨停);
		}
	}

}
