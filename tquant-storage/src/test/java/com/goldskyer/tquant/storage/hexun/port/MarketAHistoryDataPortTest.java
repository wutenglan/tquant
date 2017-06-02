package com.goldskyer.tquant.storage.hexun.port;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.hexun.dto.HexunHistoryData;

public class MarketAHistoryDataPortTest
{
	/**
	 * 测试接口数据的准确性，理论上是无懈可击的
	 * 说明：测试结果发现历史数据有部分是不准确的，6000多条中，有差不多10跳有问题，万科有一条存在严重问题。
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCheckData() throws FileNotFoundException, IOException
	{
		List<String> lines = IOUtils.readLines(new FileInputStream("/data/stock/600000.csv"));
		Map<String, DataFrame> orignMap = new HashMap<>();
		for (String line : lines)
		{
			String[] items = line.split(",");
			DataFrame df = new DataFrame();
			df.setOpen(new BigDecimal(items[3]).setScale(2, RoundingMode.HALF_UP));
			df.setClose(new BigDecimal(items[4]).setScale(2, RoundingMode.HALF_UP));
			df.setLow(new BigDecimal(items[5]).setScale(2, RoundingMode.HALF_UP));
			df.setHigh(new BigDecimal(items[6]).setScale(2, RoundingMode.HALF_UP));
			df.setVolume(new Long(items[7]) * 100);
			df.setAmount(new BigDecimal(items[8]).setScale(2, RoundingMode.HALF_UP));
			orignMap.put(items[1], df);
		}
		HexunHistoryData hexunHistoryData = new HexunHistoryData("600000.XSHG", "19800101", "20160931");
		MarketAHistoryDataPort.getHexunHistoryData(hexunHistoryData);
		System.out.println("总计历史记录：" + hexunHistoryData.getDataFrames().size());
		for (DataFrame dataFrame : hexunHistoryData.getDataFrames())
		{
			if (!dataFrame.compareBaseData(orignMap.get(dataFrame.getDateString())))
			{
				if (orignMap.get(dataFrame.getDateString()) != null)
					System.out.println(
						"不一致差额" + dataFrame.getAmount().subtract(orignMap.get(dataFrame.getDateString()).getAmount())
								+ "：dataFrame：" + dataFrame + ",orign:" + orignMap.get(dataFrame.getDateString()));
			}
		}
	}
}
