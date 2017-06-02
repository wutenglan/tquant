package com.goldskyer.tquant.storage.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldskyer.tquant.storage.entities.DataFrame;

public class ComputeTool
{
	public static Map<String, DataFrame> convert2DFMap(List<DataFrame> dfs)
	{
		Map<String, DataFrame> map = new HashMap<>();
		for (DataFrame df : dfs)
		{
			map.put(df.getSysCode(), df);
		}
		return map;
	}
}
