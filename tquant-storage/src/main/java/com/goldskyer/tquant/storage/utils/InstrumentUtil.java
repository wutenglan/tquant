package com.goldskyer.tquant.storage.utils;

import com.goldskyer.tquant.storage.enums.Exchange;

public class InstrumentUtil
{
	public static String getMarketASingleCodeBySysCode(String sysCode)
	{
		if (sysCode.indexOf(".") > 0)
		{
			return sysCode.substring(0, sysCode.indexOf("."));
		}
		return sysCode;
	}


	public static Exchange getExchangeBySysCode(String sysCode)
	{
		if (sysCode.indexOf(".") > 0)
		{
			return Exchange.valueOf(sysCode.substring(sysCode.indexOf(".") + 1));
		}
		return null;
	}

}
