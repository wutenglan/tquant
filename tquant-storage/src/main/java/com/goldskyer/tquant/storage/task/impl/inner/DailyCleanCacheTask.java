package com.goldskyer.tquant.storage.task.impl.inner;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.goldskyer.tquant.storage.cache.TickIndexCache;

public class DailyCleanCacheTask implements Job
{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{
		TickIndexCache.clean();
	}

}
