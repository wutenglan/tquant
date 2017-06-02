package com.goldskyer.tquant.storage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.service.DayDataFrameService;

/**
 * 日线数据
 * @author jintianfan
 *
 */
@Service
public class DayDataFrameServiceImpl extends BaseServiceImpl implements DayDataFrameService
{

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
}
