package com.goldskyer.tquant.storage.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.storage.business.DayDataFrameBusiness;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.service.DayDataFrameService;
import com.goldskyer.tquant.storage.sina.dto.SinaToady;
import com.goldskyer.tquant.storage.sina.port.MarketACurrentDataPort;

public class DayDataFrameBusinessImplTest extends BaseTest
{

	@Autowired
	protected DayDataFrameBusiness dayDataFrameBusiness;

	@Autowired
	protected DayDataFrameService dayDataFrameService;

	@Test
	@Transactional
	@Rollback(false)
	public void test()
	{
		long cur = System.currentTimeMillis();
		dayDataFrameBusiness.initAllDayDataFrames(Exchange.XSHE, InstrumentType.CS, "20160930", "20160930");
		System.out.println("cost:" + (System.currentTimeMillis() - cur) / 1000);
	}

	/**
	 * 测试结果说明：和讯的日线数据和新浪的日线数据是完全一致。
	 */
	@Test
	public void testCheck()
	{
		SinaToady sinaToady = MarketACurrentDataPort.getCurrentData();
		Map<String, DataFrame> orignMap = new HashMap<>();
		for (DataFrame df : sinaToady.getSinaDataFrames())
		{
			df.setDateString("20160930");
			orignMap.put(df.getSysCode(), df);
		}
		List<DataFrame> xshgs=dayDataFrameService.queryDayDataFramesByDateString("20160930", Exchange.XSHG, InstrumentType.CS);
		System.out.println("总计历史记录：" + xshgs.size());
		for (DataFrame dataFrame : xshgs)
		{
			if (!dataFrame.compareBaseData(orignMap.get(dataFrame.getSysCode())))
			{
				if (orignMap.get(dataFrame.getDateString()) != null)
				{
					System.out.println("不一致差额"
							+ dataFrame.getAmount().subtract(orignMap.get(dataFrame.getSysCode()).getAmount())
							+ "：dataFrame：" + dataFrame + ",orign:" + orignMap.get(dataFrame.getDateString()));
				}
				else
				{
					System.out.println("不一致差额,在新浪中不存在,dataFrame:" + dataFrame);
				}
			}
		}
		List<DataFrame> xshes = dayDataFrameService.queryDayDataFramesByDateString("20160930", Exchange.XSHG,
				InstrumentType.CS);
		System.out.println("总计历史记录：" + xshes.size());
		for (DataFrame dataFrame : xshes)
		{
			if (!dataFrame.compareBaseData(orignMap.get(dataFrame.getSysCode())))
			{
				if (orignMap.get(dataFrame.getDateString()) != null)
				{
					System.out.println("不一致差额"
							+ dataFrame.getAmount().subtract(orignMap.get(dataFrame.getSysCode()).getAmount())
							+ "：dataFrame：" + dataFrame + ",orign:" + orignMap.get(dataFrame.getDateString()));
				}
				else
				{
					System.out.println("不一致差额,在新浪中不存在,dataFrame:" + dataFrame);
				}
			}
		}
	}

	/**
	 * 测试单条数据初始化成功，
	 */
	@Test
	public void testInitDayDataFramesBySysCode()
	{
		dayDataFrameBusiness.initDayDataFramesBySysCode("000002.XSHE", "19960101", "20161010");
	}

	/**
	 * 初始化所有的日线数据
	 */
	public void testInitAllDayDataFrames()
	{
		List<Instrument> instruments = instrumentService.queryInstrumentsByType(Exchange.XSHG, InstrumentType.CS);
		for (Instrument instrument : instruments)
		{
			dayDataFrameBusiness.initDayDataFramesBySysCode(instrument.getSysCode(), "19960101", "20160930");
		}
		List<Instrument> instrument2s = instrumentService.queryInstrumentsByType(Exchange.XSHE, InstrumentType.CS);
		for (Instrument instrument : instrument2s)
		{
			dayDataFrameBusiness.initDayDataFramesBySysCode(instrument.getSysCode(), "19960101", "20160930");
		}
	}


}
