package com.goldskyer.tquant.storage.cache;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.goldskyer.tquant.storage.exception.TickOutOfRangeException;
import com.goldskyer.tquant.storage.monitor.vo.TickMonitorVo;

/**
 * tick数据缓存器。
 * 描述：因为数据量大，所以需要定制。
 * 需要自动移除10分钟外的数据，防止占用内存过大。
 * @author jintianfan
 *
 */
public class TickMonitorVoCache
{
	public static final Map<String, TickMonitorVo> dataMap = new ConcurrentHashMap<>();

	public static void put(String key, TickMonitorVo v)
	{
		dataMap.put(key, v);
	}

	public static TickMonitorVo get(String key)
	{
		return dataMap.get(key);
	}

	/**
	 * 删除tickCnt周期以外的对象
	 * @param ticks
	 */
	private static void cleanCache(int tickCnt)
	{
		Date date=new Date();
		int tick = 0;
		try
		{
			tick = TickIndexCache.getTickIdByDate(date);
			Iterator<Map.Entry<String, TickMonitorVo>> it = dataMap.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<String, TickMonitorVo> entry = it.next();
				String key = entry.getKey();
				if (Integer.valueOf(key) < tick - tickCnt)
				{
					it.remove();
				}
			}
		}
		catch (TickOutOfRangeException te)
		{
			clear(); //股市收盘，全部清空数据
		}

	}

	public static void clear()
	{
		dataMap.clear();
	}
	static
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run()
			{
				cleanCache(60 * 10);//删除10分钟以外的tick缓存数据
			}
		}, 0, 1000 * 60);//1分钟执行一次
	}
}
