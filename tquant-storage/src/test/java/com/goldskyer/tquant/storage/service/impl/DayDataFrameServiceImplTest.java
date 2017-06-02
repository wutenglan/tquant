package com.goldskyer.tquant.storage.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.storage.entities.DataFrame;
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
}
