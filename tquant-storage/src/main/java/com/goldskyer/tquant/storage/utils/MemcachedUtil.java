package com.goldskyer.tquant.storage.utils;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil
{
	private static final Log log = LogFactory.getLog(MemcachedUtil.class);
	public final static int DEFAULT_TIMEOUT = 24 * 60 * 60 * 1000;// 默认超时时间 24小时
	protected static MemCachedClient mc = null;
	protected static SockIOPool pool = null;

	static
	{
		log.debug("    MemcachedService.init()");
//		Properties prop = new Properties();  
//		try{
//			 InputStream in = new BufferedInputStream(new FileInputStream(""));
//			 prop.load(in);
//		}catch(Exception e)
//		{
//			log.fatal(e.getMessage(),e);
//		}
		//设置每个参数的默认值

		//超过64K则启用压缩
		int compressThreshold = 64000;


		//将参数值set到pool中
		String[] servers = new String[]{"127.0.0.1:11211"};
		boolean ifFailOver = true;
		boolean ifFailback = true;
		// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机   
		// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，   
		// 以至这个包准备好了就发；  
		boolean ifNagle = true;
		boolean ifAliveCheck = true;
		boolean ifCompressEnable = true;
		boolean ifPrimitiveAsString = true;
		//服务器权重
		String[] weightStr = new String[]{"1"};
		Integer[] weightInt = new Integer[weightStr.length];
		for (int i = 0; i < weightStr.length; i++)
		{
			weightInt[i] = Integer.parseInt(weightStr[i].trim());
			log.info(weightInt[i]);
		}

		//处理字符串前后的空格
		for (int i = 0; i < servers.length; i++)
		{
			servers[i] = servers[i].trim();
		}
		pool = SockIOPool.getInstance();
		pool.setServers(servers);
		if (servers.length > 1)
		{
			pool.setWeights(weightInt);
		}
//		pool.setInitConn(initConn);
//		pool.setMinConn(minConn);
//		pool.setMaxConn(maxConn);
//		pool.setMaxIdle(maxIdle);
//		pool.setMaxBusyTime(maxBusyTime);
//		pool.setMaintSleep(maintSleep);
		pool.setNagle(ifNagle);
		
		pool.setFailover(ifFailOver);
		pool.setFailback(ifFailback);
		pool.setAliveCheck(ifAliveCheck);
		pool.initialize();

		mc = new MemCachedClient();
		mc.setCompressEnable(ifCompressEnable);
		if (ifCompressEnable)
		{
			mc.setCompressThreshold(compressThreshold);
		}
		mc.setPrimitiveAsString(ifPrimitiveAsString);

	}
	
	/**
	 * 用key获取mem cache中的value
	 */
	public static Object getValue(String key)
	{
		try
		{
			return mc.get(key);
		}
		catch (Exception e)
		{
			log.fatal("MemcachedUtil.getValue(String)：获取memcache中的值异常，key:" + key, e);
			return null;
		}
	}
	public static void setValue(String key, Object value)
	{
		setValue(key, value, DEFAULT_TIMEOUT);
	}
	
	/**
	 * 将键值对存入mem cache
	 */
	public static void setValue(String key, Object value, long timeOut)
	{
		try
		{
			mc.set(key, value, new Date(System.currentTimeMillis() + timeOut));
		}
		catch (Exception e)
		{
			log.fatal("MemcachedUtil.setValue(String,Object)：往memcache中赋值异常，key:" + key + "|value:" + value, e);
		}
	}


	/**
	 * 将mem cache中key对应的键值对删除
	 */
	public static void removeValue(String key)
	{
		try
		{
			mc.delete(key);
		}
		catch (Exception e)
		{
			log.fatal("MemcachedUtil.removeValue(String)：从memcache中删除异常，key:" + key, e);
		}
	}
	
	public static void main(String[] args) {
		MemcachedUtil.setValue("JIN", 1);
		System.out.println(MemcachedUtil.getValue("JIN"));
	}

	
}