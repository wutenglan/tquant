package com.goldskyer.tquant.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringAndNumUtils {

	public static List<String> splitStringToList(String value,String regex)
	{
		List<String> list = new ArrayList<String>();
		if(value==null)
		{
			return list;
		}
		
		String[] vals= value.split(regex);
		for(String val:vals)
		{
			list.add(StringUtils.trim(val));
		}
		return list;
	}
	
	public static String listToString(List<String> list,String split)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1)
			{
				sb.append(list.get(i));
			}
			else
			{
				sb.append(StringUtils.trim(list.get(i))).append(split);
			}
		}
		return sb.toString();
	}
}
