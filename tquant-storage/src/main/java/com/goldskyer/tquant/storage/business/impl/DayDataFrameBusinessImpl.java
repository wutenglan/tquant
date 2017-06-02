package com.goldskyer.tquant.storage.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.tquant.storage.business.DayDataFrameBusiness;
import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.hexun.dto.HexunHistoryData;
import com.goldskyer.tquant.storage.hexun.port.MarketAHistoryDataPort;
import com.goldskyer.tquant.storage.service.DayDataFrameService;

/**
 * 日线数据业务类
 * @author jintianfan
 *
 */
@Service
public class DayDataFrameBusinessImpl extends BaseBusinessImpl implements DayDataFrameBusiness
{
	@Autowired
	private DayDataFrameService dayDataFrameService;


	/**
	 * 保存当前时间日线数据
	 * @param dayDataFrames
	 */
	public void saveCurrentDayDateFrames()
	{

	}
	
	/**
	 * 重置特定市场指定时间区间的日线数据
	 * @param exchange
	 * @param type
	 * @param startDateStr
	 * @param endDateStr
	 */
	@Transactional
	public void initAllDayDataFrames(Exchange exchange, InstrumentType type,String startDateStr, String endDateStr)
	{
		List<Instrument> instruments = instrumentService.queryInstrumentsByType(exchange, type);
		for (Instrument instrument : instruments)
		{
			initDayDataFramesBySysCode(instrument.getSysCode(), startDateStr, endDateStr);
		}
	}

	@Transactional
	public void deleteDayDataFrames(Exchange exchange, InstrumentType type, String startDateStr, String endDateStr)
	{
		List<Instrument> instruments = instrumentService.queryInstrumentsByType(exchange, type);
		for (Instrument instrument : instruments)
		{
			dayDataFrameService.deleteDataFrames(instrument.getSysCode(), startDateStr, endDateStr);
		}
	}

	@Override
	@Transactional
	public void initDayDataFramesBySysCode(String sysCode, String startDateStr, String endDateStr)
	{
		//删除已有的数据
		dayDataFrameService.deleteDataFrames(sysCode, startDateStr, endDateStr);
		HexunHistoryData hexunHistoryData = new HexunHistoryData(sysCode, startDateStr, endDateStr);
		MarketAHistoryDataPort.getHexunHistoryData(hexunHistoryData);
		baseDao.add(hexunHistoryData.getDataFrames());
	}
}
