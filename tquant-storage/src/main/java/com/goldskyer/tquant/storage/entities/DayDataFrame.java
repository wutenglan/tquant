package com.goldskyer.tquant.storage.entities;

import java.math.BigDecimal;
/**
 * 日线数据帧
 * @author jintianfan
 *
 */
public class DayDataFrame extends FullDataFrame{

	@Override
	public String toString() {
		return "DayDataFrame [sysCode=" + sysCode + ", open=" + open + ", close=" + close + ", high=" + high + ", low="
				+ low + ", volume=" + volume + ", amount=" + amount + ", dateString=" + dateString + "]";
	}

	
	
	
	
}
