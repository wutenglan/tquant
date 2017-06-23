package com.goldskyer.tquant.storage.service;

import java.math.BigDecimal;
import java.util.List;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.DayDataFrame;
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
	
	/**
	 * 从存储中获取某日的日线数据
	 * @param dateString
	 * @return
	 */
	public List<DayDataFrame> queryDayDataFrameFromStorage(Exchange exchange,String dateString);
	
	/**
	 * 查询某日涨停的个股
	 * @param exchange
	 * @param dateString
	 * @return
	 */
	public  List<DayDataFrame> querySurgeLimit(Exchange exchange,String dateString);
	
	/**
	 * 查询连续涨停的个股
	 * @param exchange
	 * @param dateString
	 * @param percent
	 * @return
	 */
	public  List<DayDataFrame> querySurgeLimitContinous(Exchange exchange,String dateString,BigDecimal percent);

}
