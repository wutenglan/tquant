package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;

public interface DayDataFrameService
{

	/**
	 * 查询指定交易所的日线数据
	 * @param dateString 日期
	 * @param exchange 交易所名称
	 * @return
	 */
	public List<DataFrame> queryDayDataFramesByDateString(String dateString, Exchange exchange,
			InstrumentType instrumentType);

	public void deleteDataFrames(String sysCode, String startDateStr, String endDateStr);

}
