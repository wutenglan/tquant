package com.goldskyer.tquant.storage.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractScheduleTask implements Job
{

	public static final String CRON_TASK = "CRON_TASK";


	//执行具体任务
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{
		if (isValidDay() && isValidTime())
		{
			executeInner();
		}
	}

	public abstract void executeInner();

	public abstract boolean isValidTime();


	public abstract boolean isValidDay();


}
