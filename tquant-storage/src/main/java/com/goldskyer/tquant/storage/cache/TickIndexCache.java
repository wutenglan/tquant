package com.goldskyer.tquant.storage.cache;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.storage.exception.TickOutOfRangeException;
import com.goldskyer.tquant.storage.monitor.vo.TickIndex;

public class TickIndexCache
{
	private static final Log log=LogFactory.getLog(TickIndexCache.class);
	private static final List<TickIndex> indexList = new ArrayList<>();
	static
	{
		clean();
	}


	public synchronized static void setTick(Date date)
	{
		int dataTickId = getTickIdByDate(date);
		fillEmptyUnit(dataTickId);
		//printInner();
	}

	private synchronized static void fillEmptyUnit(int dataTickId)
	{
		indexList.get(dataTickId).setDataTickId(dataTickId);
		for(int i=1;i<dataTickId;i++)
		{
			if(indexList.get(i).getDataTickId()==TickIndex.EMPTY_DATA_TICK_ID)
			{
				indexList.get(i).setDataTickId(indexList.get(i - 1).getDataTickId());
			}
		}
	}

	private static void printInner()
	{
		for (TickIndex i : indexList)
		{
			System.out.println(i.getTickId() + ":" + i.getDataTickId());
		}
	}

	/**
	 * 获取上一个
	 * @param curTickId
	 * @param peroid
	 * @return
	 */
	public static int getPreDataTickId(int curTickId, int peroid)
	{
		if (curTickId - peroid < TickIndex.MIN_TICK_ID)
		{
			return TickIndex.MIN_TICK_ID;
		}
		if (curTickId - peroid > TickIndex.MAX_TICK_ID)
		{
			return TickIndex.MAX_TICK_ID;
		}
		return indexList.get(curTickId - peroid).getDataTickId();
	}

	/**
	 * 
	 * @param time HHMMSS
	 * @return
	 */
	public static int getTickIdByTime(String time)
	{
		return getTickIdByDate(DateUtil.string2Date("20160101"+time, "yyyyMMddHHmmSS"));
	}
	
	
	
	/**
	 * 根据日期，获取当前的tickId
	 * 9:25:00 为0
	 * 说明：11：30和1：00共享一个单元，允许覆盖
	 * @param date
	 * @return
	 */
	public static int getTickIdByDate(Date date)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int second = calendar.get(Calendar.SECOND);
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int result = -1;
		if (hour == 9 || hour == 10 || hour == 11) //
		{
			result = ((hour - 9) * 3600 + (minute - 25) * 60 + second); //最后一个是7500（11）
		}
		if (hour == 13 || hour == 14 || hour == 15)
		{
			result = ((hour - 13) * 3600 + minute * 60 + second + 7500);
		}
		if (result < TickIndex.MIN_TICK_ID || result > TickIndex.MAX_TICK_ID)
		{
			throw new TickOutOfRangeException("该Date对应无效的tick");
		}
		return result;
	}
	
	public static String getTimeByTickId(int tickId)
	{
		int cal=tickId;
		if(tickId>7500)
		{
			cal+=90*60;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 25);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, cal);
		return DateUtil.formatDate(calendar.getTime(), "HHmmss");
	}

	public static void clean()
	{
		log.info("执行tickIndex清理");
		indexList.clear();
		for (int i = TickIndex.MIN_TICK_ID; i <= TickIndex.MAX_TICK_ID; i++)
		{
			TickIndex index = new TickIndex();
			index.setTickId(i);
			index.setDataTickId(TickIndex.EMPTY_DATA_TICK_ID);
			indexList.add(index);
		}
	}


	public static void main(String[] args)
	{
		System.out.println(getTimeByTickId(14760));
		System.out.println(getTickIdByDate(DateUtil.string2Date("20090101150100", "yyyyMMddHHmmss")));
	}
}
