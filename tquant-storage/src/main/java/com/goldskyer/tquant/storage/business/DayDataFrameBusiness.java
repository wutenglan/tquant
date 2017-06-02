package com.goldskyer.tquant.storage.business;

import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;

public interface DayDataFrameBusiness
{

	/**
	 * 重置特定市场指定时间区间的日线数据
	 * 适用场景：重置所有行情数据，重置某日行情数据，补全某日行情数据
	 * @param exchange
	 * @param type
	 * @param startDateStr
	 * @param endDateStr
	 */
	public void initAllDayDataFrames(Exchange exchange, InstrumentType type, String startDateStr, String endDateStr);

	/**
	 * 重置某只股票指定时间区间的日线数据
	 * @param sysCode
	 */
	public void initDayDataFramesBySysCode(String sysCode, String startDateStr, String endDateStr);

	public void deleteDayDataFrames(Exchange exchange, InstrumentType type, String startDateStr, String endDateStr);

}
