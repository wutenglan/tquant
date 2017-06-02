package com.goldskyer.tquant.base;

import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class DiaoduTest implements Job
{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		CronTrigger trigger = (CronTrigger) context.get("trigger");
		System.out.println(context.getJobDetail().getName());
		System.out.println(new Date() + "天佑最帅！！！");

	}

	public static void main(String[] args)
	{
		JobDetail detail = new JobDetail("job1", "group1", DiaoduTest.class);
		JobDetail detail2 = new JobDetail("job2", "group1", DiaoduTest.class);

		CronTrigger cronTrigger = new CronTrigger("job1", "group1");
		try
		{
			CronExpression cronExpression = new CronExpression("* * * * *  ?");
			cronTrigger.setCronExpression(cronExpression);
			CronTrigger cronTrigger2 = new CronTrigger("job2", "group2");
			CronExpression cronExpression2 = new CronExpression("* * * * *  ?");
			cronTrigger2.setCronExpression(cronExpression2);
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler;
			try
			{
				scheduler = factory.getScheduler();
				try
				{
					scheduler.scheduleJob(detail, cronTrigger);
					scheduler.scheduleJob(detail2, cronTrigger2);
					scheduler.start();
				}
				catch (SchedulerException e)
				{
					e.printStackTrace();
				}
			}
			catch (SchedulerException e)
			{
				e.printStackTrace();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}