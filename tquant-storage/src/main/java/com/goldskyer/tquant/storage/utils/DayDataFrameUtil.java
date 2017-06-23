package com.goldskyer.tquant.storage.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.DayDataFrame;

public class DayDataFrameUtil  {
	private static final Log log=LogFactory.getLog(DayDataFrameUtil.class);
	

	
	public static DayDataFrame build(DataFrame df)
	{
		DayDataFrame day=new DayDataFrame();
		try {
			BeanUtils.copyProperties(day, day);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} 
		return day;
	}
}
