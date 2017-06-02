package com.goldskyer.tquant.storage.utils;

import com.goldskyer.tquant.storage.enums.BoardType;
import com.goldskyer.tquant.storage.enums.Exchange;

/**
 * 中国市场工具类
 * @author jintianfan
 *
 */
public class MarketCnUtil
{
	/**
	 * 把国内的股票编码转为国际通用编码
	 * 600000 -> 600000.XSHG
	 * @param code
	 * @return
	 */
	public static String getSysCodeByMarketCnCode(String code)
	{
		Exchange exchange = getExchangeByMarketCnCode(code);
		return code + "." + exchange.name();
	}

	public static String getMarketCnCodeBySysCode(String sysCode)
	{
		return sysCode.substring(0, sysCode.indexOf("."));
	}

	/**
	 * 中国国内编码获取交易所名称
	 * @param code
	 * @return
	 */
	public static Exchange getExchangeByMarketCnCode(String code)
	{
		String prefix = code.substring(0, 3);
		if (prefix.equals("600") || prefix.equals("601") || prefix.equals("603") || prefix.equals("900"))
		{
			return Exchange.XSHG;
		}
		else if (prefix.equals("000") || prefix.equals("001") || prefix.equals("002") || prefix.equals("300")
				|| prefix.equals("200"))
		{
			return Exchange.XSHE;
		}
		else
		{
			return Exchange.OTHER;
		}
	}

	public static BoardType getBoardTypeByMarketCnCode(String code)
	{
		String prefix = code.substring(0, 3);
		if (prefix.equals("300"))
		{
			return BoardType.GEM;
		}
		else if (prefix.equals("002"))
		{
			return BoardType.SMALL_BOARD;
		}
		else
		{
			return BoardType.MAIN_BOARD;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(getMarketCnCodeBySysCode("asdasd.SSS"));
	}


}
