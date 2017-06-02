package com.goldskyer.tquant.storage.utils;

/**
 * 把syscode转为shortCode，节约存储空间
 * @author jintianfan
 *
 */
public class ShortCodeUtil
{
	/**
	 * 映射规则。为方便（目前针对A股做特殊映射）
	 * 前4个为市场
	 * @param sysCode
	 * @return
	 */
	public static final Integer getShortCode(String sysCode)
	{
		String marketACode = InstrumentUtil.getMarketASingleCodeBySysCode(sysCode);

		if (marketACode.startsWith("000"))
		{

		}
		return null;
	}


}
