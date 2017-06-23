package com.goldskyer.tquant.storage.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.DayDataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.service.DayDataFrameService;

import junit.framework.Assert;

public class DayDataFrameServiceImplTest extends BaseTest
{
	@Autowired
	private DayDataFrameService dayDataFrameService;

	@Test
	public void testAdd()
	{
		DataFrame df=new DataFrame();
		df.setAmount(new BigDecimal("12"));
		df.setDateString("20160501");
		df.setSysCode("600000.XSHE");
		baseDao.add(df);
	}

	@Test
	public void testQueryDayDataFramesByDateString()
	{
		List<DataFrame> dataFrames = dayDataFrameService.queryDayDataFramesByDateString("20160930", Exchange.XSHE,
				InstrumentType.CS);
		Assert.assertTrue(dataFrames.size() > 1000);
	}
	
	@Test
	public void testQuerySurgeLimit()
	{
		List<DayDataFrame> dataFrames=dayDataFrameService.querySurgeLimit(Exchange.XSHG, "20170621");
		
		System.out.println("找到涨停的个股"+dataFrames.size());
		System.out.println(dataFrames);
	}
	
	@Test
	public void testQuerySurgeLimitContinuse()
	{
		List<DayDataFrame> dataFrames=dayDataFrameService.querySurgeLimitContinous(Exchange.XSHG, "20170622",new BigDecimal("0.08"));
		System.out.println("找到连续涨停的个股"+dataFrames.size());
		System.out.println(dataFrames);
	}
}
