package com.goldskyer.tquant.storage.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goldskyer.tquant.storage.task.CronTaskManager;
import com.goldskyer.tquant.storage.utils.SpringContextHolder;

/**
 * maven多模块打包后 spring扫描不到Jar包里的Bean 
原因：打包时没有生成目录信息
解决办法：
在eclipse 打包时 可以勾选 ADD DIRECTORY ENTRIES,maven下参考：http://zjfhw.iteye.com/blog/2246268
 * @author jintianfan
 *
 */
public class Boot
{
	private static Log log = LogFactory.getLog(Boot.class);

	public static void main(String[] args)
	{
		try
		{
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]
			{ "tquant/storage/applicationContext.xml" });
			context.start();
			log.info("Spring初始化完成。Bean数量：" + context.getBeanDefinitionCount());
			StringBuffer sb=new StringBuffer();
			for(String beanName:context.getBeanDefinitionNames())
			{
				sb.append(beanName).append(",");
			}
			log.info("加载的Bean明细："+sb.toString());
			SpringContextHolder.setContext(context);
			SpringContextHolder.getBean(CronTaskManager.class).start();
			log.info("tquant-storage服务启动");
		}catch(Exception e)
		{
			log.fatal("系统启动失败.e:" + e.getMessage(), e);
		}

	}
}
