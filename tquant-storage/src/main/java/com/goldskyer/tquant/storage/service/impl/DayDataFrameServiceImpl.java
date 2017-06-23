package com.goldskyer.tquant.storage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.DayDataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.service.DayDataFrameService;
import com.goldskyer.tquant.storage.service.StorageFileService;
import com.goldskyer.tquant.storage.service.WeekendService;
import com.goldskyer.tquant.storage.utils.DataFrameUtil;
import com.goldskyer.tquant.storage.utils.DayDataFrameUtil;
import com.goldskyer.tquant.storage.utils.FullDataFrameUtil;

/**
 * 日线数据
 * @author jintianfan
 *
 */
@Service
@SuppressWarnings("unchecked")
public class DayDataFrameServiceImpl extends BaseServiceImpl implements DayDataFrameService
{
	private static final Log log=LogFactory.getLog(DayDataFrameServiceImpl.class);
	
	@Autowired
	private StorageFileService storageFileService;
	@Autowired
	private WeekendService weekendService;
	
	public List<DataFrame> queryDayDataFramesByDateString(String dateString, Exchange exchange, InstrumentType type)
	{
		return baseDao
				.query("select t from DataFrame t where t.dateString=? and  exists (select b.sysCode from Instrument b where b.type=? and b.exchange=? and b.sysCode=t.sysCode )",
						dateString, type, exchange);
	}

	@Override
	public void deleteDataFrames(String sysCode, String startDateStr, String endDateStr)
	{
		baseDao.execute("delete from DataFrame where  sysCode=? and dateString>=? and dateString<=?", sysCode,
				startDateStr, endDateStr);
	}

	@Override
	public List<DayDataFrame> queryDayDataFrameFromStorage(Exchange exchange,String dateString) {
		List<DayDataFrame> list=new ArrayList<DayDataFrame>();
		List<DayDataFrame> dfs=storageFileService.getCloseMarketDataFrames(dateString,DayDataFrame.class);
		String preDay=weekendService.getPreTradingDateString(exchange, dateString);
		List<DayDataFrame> predfs=storageFileService.getCloseMarketDataFrames(preDay,DayDataFrame.class);
		Map<String, DayDataFrame> map=DataFrameUtil.convertList2Map(predfs);
		for(DayDataFrame df:dfs)
		{
			if(map.containsKey(df.getSysCode()))
			{
				df.setPreClose(map.get(df.getSysCode()).getClose());
			}
			list.add(df);
		}
		return list;
	}
	
	public  List<DayDataFrame> querySurgeLimit(Exchange exchange,String dateString)
	{
		List<DayDataFrame> list=new ArrayList<>();
		List<DayDataFrame>  days=queryDayDataFrameFromStorage(exchange, dateString);
		for(DayDataFrame day:days)
		{
			if(FullDataFrameUtil.isSurgeLimit(day))
			{
				list.add(day);
			}
		}
		return list;
	}
	
	public  List<DayDataFrame> querySurgeLimitContinous(Exchange exchange,String dateString,BigDecimal percent)
	{
		String preDay=weekendService.getPreTradingDateString(exchange, dateString);
		List<DayDataFrame> result=new ArrayList<>();
		List<DayDataFrame>  days=querySurgeLimit(exchange, preDay);//昨日涨停的个股
		List<DayDataFrame> todays=queryDayDataFrameFromStorage(exchange, dateString);
		Map<String, DayDataFrame> todayMap=DataFrameUtil.convertList2Map(todays);
		for(DayDataFrame day:days)
		{
			if(todayMap.containsKey(day.getSysCode()))
			{
				if((todayMap.get(day.getSysCode()).getClose().subtract(day.getClose())).divide(day.getClose(), BigDecimal.ROUND_HALF_DOWN).compareTo(percent)>=0)
				{
					result.add(day);
				}
			}
			else
			{
				log.info("昨日涨停，今日没有数据code:"+day.getSysCode());
			}
		}
		return result;
	}
	
}
