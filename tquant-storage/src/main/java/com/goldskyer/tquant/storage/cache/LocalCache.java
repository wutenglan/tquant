package com.goldskyer.tquant.storage.cache;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 先使用本地缓存，如果不行再换别的方法
 * @author jintianfan
 *
 */
public class LocalCache
{
	public static final Map<String, Object> dataMap = new ConcurrentHashMap<>();
	
	public static void put(String key, Object v)
	{
		dataMap.put(key, v);
	}

	public static Object get(String key)
	{
		return dataMap.get(key);
	}
}
