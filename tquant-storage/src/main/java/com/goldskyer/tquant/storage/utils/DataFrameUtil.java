package com.goldskyer.tquant.storage.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldskyer.tquant.storage.entities.DataFrame;

public class DataFrameUtil {
	
	/**
	 * 把DFS LIST转为MAP
	 * @param dfs
	 * @return
	 */
//	public static Map<String, DataFrame> convertList2Map(List< DataFrame> dfs)
//	{
//		Map<String, DataFrame> map=new HashMap<>();
//		if(dfs==null ||dfs.isEmpty())
//			return map;
//		for(DataFrame df:dfs)
//		{
//			map.put(df.getSysCode(), df);
//		}
//		return map;
//	}
	
	/**
	 * 把DFS LIST转为MAP
	 * @param dfs
	 * @return
	 */
	public static <T extends DataFrame> Map<String, T > convertList2Map(List<T> dfs)
	{
		Map<String, T> map=new HashMap<>();
		if(dfs==null ||dfs.isEmpty())
			return map;
		for(T df:dfs)
		{
			map.put(df.getSysCode(), df);
		}
		return map;
	}
}
