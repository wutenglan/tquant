package com.goldskyer.tquant.storage.utils;

import java.math.BigDecimal;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.FullDataFrame;

/**
 * DataFrame工具类
 * @author jintianfan
 *
 */
public class FullDataFrameUtil {
	
	/**
	 * 判断当前股票是否涨停,四舍五入>0.5
	 * @param df
	 * @return
	 */
	public static boolean isSurgeLimit(FullDataFrame fdf)
	{
		if(fdf.getPreClose()==null)
		{
			return false;
		}
		return fdf.getClose().compareTo(
				fdf.getPreClose().multiply(new BigDecimal(1.1))
				.setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0;
	}
}
