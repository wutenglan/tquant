package com.goldskyer.tquant.storage.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.StaticApplicationContext;

public class ReflectUtil {
	private static final Log log=LogFactory.getLog(ReflectUtil.class);
	
	public static <T> T createInstance(Class<T> cls)
	{
		T obj=null;
		try{
			obj=cls.newInstance();
		}catch(Exception e)
		{
			log.error(e.getMessage(),e);
		}
		return obj;
	}
}
