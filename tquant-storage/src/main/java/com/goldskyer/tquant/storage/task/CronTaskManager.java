package com.goldskyer.tquant.storage.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldskyer.tquant.storage.entities.CronTask;
import com.goldskyer.tquant.storage.service.CronTaskService;
import com.goldskyer.tquant.storage.task.impl.inner.DailyCleanCacheTask;
import com.goldskyer.tquant.storage.utils.SpringContextHolder;

@Component
public class CronTaskManager
{
	private static Log log = LogFactory.getLog(CronTaskManager.class);
	@Autowired
	private CronTaskService cronTaskService;
	private Scheduler scheduler;
	/**
	 * 在Spring初始化完成后调用
	 * @throws Exception 
	 */
	public void start() throws Exception
	{
		List<CronTask> cronTasks = cronTaskService.queryValidCronTasks();
		SchedulerFactory factory = new StdSchedulerFactory();
		scheduler = factory.getScheduler();
		for(CronTask task:cronTasks)
		{
			try
			{
				JobDetail detail = new JobDetail(task.getTaskName(), task.getGroup(),
						SpringContextHolder.getBean(task.getTaskBean()).getClass());
				detail.getJobDataMap().put(AbstractScheduleTask.CRON_TASK, task);
				CronTrigger cronTrigger = new CronTrigger(task.getTaskName(), task.getGroup());
				CronExpression cronExpression = new CronExpression(task.getExpress());
				cronTrigger.setCronExpression(cronExpression);
				scheduler.scheduleJob(detail, cronTrigger);
				log.info("加入一个CronTask:" + task.getTaskName());
				loadInnerCronTask();
			}
			catch (Exception e)
			{
				log.fatal("定时任务启动失败，当前任务名：" + task.getTaskName() + "e:" + e.getMessage(), e);
			}
		}
		scheduler.start();
	}

	/**
	 * 加载程序内置的定时任务，菲通过数据库配置
	 */
	private void loadInnerCronTask() throws Exception
	{
		CronTrigger cronTrigger = new CronTrigger("DailyCleanCacheTask", "inner");
		CronExpression cronExpression = new CronExpression("0 10 0 * * ?");
		cronTrigger.setCronExpression(cronExpression);
		JobDetail detail = new JobDetail("DailyCleanCacheTask", "inner", DailyCleanCacheTask.class);
		scheduler.scheduleJob(detail, cronTrigger);
	}
}
