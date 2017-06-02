package com.goldskyer.tquant.storage.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("unchecked")
public class SpringContextHolder {

	private static ClassPathXmlApplicationContext context;


	/**
	 * 获取spring中bean的实例
	 * 
	 * @param beanName
	 * @param sc
	 * @return
	 */
	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz)
	{
		return (T) context.getBean(clazz);
	}

	public ClassPathXmlApplicationContext getContext()
	{
		return context;
	}

	public static void setContext(ClassPathXmlApplicationContext context)
	{
		SpringContextHolder.context = context;
	}

}
