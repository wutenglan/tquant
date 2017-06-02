package com.goldskyer.tquant.storage.hexun.port;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.common.exceptions.NetException;
import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.common.util.HttpUtil;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.hexun.dto.HexunHistoryData;
import com.goldskyer.tquant.storage.utils.InstrumentUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MarketAHistoryDataPort
{
	private static Log log = LogFactory.getLog(MarketAHistoryDataPort.class);

	public static final String HEXUN_RAW_STOACK_PORT = "http://webstock.quote.hermes.hexun.com/a/kline?code=%s&start=%s000000&number=%s&type=5";

	/**
	 * 
	 * 由于和讯数据接口存在单次访问最大数据量限制,和讯返回数据为默认升序
	 * 原理：该和讯接口在起始时间为比较久远时候比如1980，会自动获取有效时间区间内的数据，不需要在乎起始时间
	 * @param code
	 * @param startDateStr yyyyMMdd
	 * @param endDateString yyyyMMdd
	 * @return
	 */
	public static void getHexunHistoryData(final HexunHistoryData hexunHistoryData)
	{
		if (hexunHistoryData.getEndDateStr().compareTo(hexunHistoryData.getStartDateStr()) < 0)
		{
			hexunHistoryData.isFinished();
			return;
		}
		int betweenDays = DateUtil.betweenDays(hexunHistoryData.getStartDateStr(), hexunHistoryData.getEndDateStr());
		String sb = new String(); //存放请求响应数据

		// 组装url
		String codeParam = "";
		if (InstrumentUtil.getExchangeBySysCode(hexunHistoryData.getSysCode()) == Exchange.XSHG)
		{
			codeParam = "sse" + InstrumentUtil.getMarketASingleCodeBySysCode(hexunHistoryData.getSysCode());
		}
		else if (InstrumentUtil.getExchangeBySysCode(hexunHistoryData.getSysCode()) == Exchange.XSHE)
		{
			codeParam = "szse" + InstrumentUtil.getMarketASingleCodeBySysCode(hexunHistoryData.getSysCode());
		}
		String url = String.format(HEXUN_RAW_STOACK_PORT, codeParam, hexunHistoryData.getCurDateStr(), betweenDays);
		try
		{
			sb = HttpUtil.openUrlReturnMoreMessage(url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NetException(e.getMessage(), e);
		}
		JSONObject jsonObject = JSONObject.fromObject(sb.toString().substring(1, sb.toString().lastIndexOf(")")));
		JSONArray arr = jsonObject.getJSONArray("Data");
		if (arr == null || arr.isEmpty())
		{
			hexunHistoryData.setFinished(true);
			return;
		}
		JSONArray rawArr = arr.getJSONArray(0);
		if (rawArr.isEmpty())
		{
			return;
		}
		String publicDateString = arr.getString(1).substring(0, 8);
		hexunHistoryData.setPublicDateStr(publicDateString); //设置上市日期
		for (int i = 0; i < rawArr.size(); i++)
		{
			JSONArray o = rawArr.getJSONArray(i);
			DataFrame k = new DataFrame();
			k.setSysCode(hexunHistoryData.getSysCode());
			k.setDateString(o.getString(0).substring(0, 8));
			k.setOpen(new BigDecimal(o.getInt(2) / 100.0).setScale(2, RoundingMode.HALF_UP));
			k.setClose(new BigDecimal(o.getInt(3) / 100.0).setScale(2, RoundingMode.HALF_UP));
			k.setHigh(new BigDecimal(o.getInt(4) / 100.0).setScale(2, RoundingMode.HALF_UP));
			k.setLow(new BigDecimal(o.getInt(5) / 100.0).setScale(2, RoundingMode.HALF_UP));
			k.setVolume(o.getLong(6) * 100);
			k.setAmount(new BigDecimal(o.getDouble(7)).setScale(2, RoundingMode.HALF_UP));
			if (k.getDateString().compareTo(hexunHistoryData.getEndDateStr()) > 0) //超过了查询区间，则退出
			{
				hexunHistoryData.setFinished(true);
				break;
			}
			else if (k.getDateString().compareTo(hexunHistoryData.getEndDateStr()) == 0)
			{
				hexunHistoryData.setFinished(true);
				hexunHistoryData.getDataFrames().add(k);
				break;
			}
			else
			{
				hexunHistoryData.getDataFrames().add(k);
			}

		}
		//判断是否达到endDateStr
		if (!hexunHistoryData.isFinished())
		{
			hexunHistoryData.setCurDateStr(
					hexunHistoryData.getDataFrames().get(hexunHistoryData.getDataFrames().size() - 1).getDateString());
			hexunHistoryData.setCurDateStr(DateUtil.getDateStrInterval(hexunHistoryData.getCurDateStr(), 1));
			getHexunHistoryData(hexunHistoryData);
		}
	}

	public static void main(String[] args) throws Exception
	{
		List<String> lines = IOUtils.readLines(new FileInputStream("/data/stock/good.csv"));
		HexunHistoryData hexunHistoryData = new HexunHistoryData("000002.XSHE", "19800101", "20160931");
		getHexunHistoryData(hexunHistoryData);
		System.out.println("");
		for (DataFrame dataFrame : hexunHistoryData.getDataFrames())
		{
			System.out.println(dataFrame);
		}
	}
}
