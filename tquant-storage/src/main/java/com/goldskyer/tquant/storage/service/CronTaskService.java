package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.CronTask;

public interface CronTaskService
{
	public List<CronTask> queryValidCronTasks();
}
