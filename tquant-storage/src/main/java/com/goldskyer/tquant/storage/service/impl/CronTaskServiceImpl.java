package com.goldskyer.tquant.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.dao.BaseDao;
import com.goldskyer.tquant.storage.entities.CronTask;
import com.goldskyer.tquant.storage.enums.Switch;
import com.goldskyer.tquant.storage.service.CronTaskService;

@Service
public class CronTaskServiceImpl implements CronTaskService
{
	@Autowired
	private BaseDao baseDao;
	@Override
	public List<CronTask> queryValidCronTasks()
	{
		// TODO Auto-generated method stub
		return baseDao.query("select t from CronTask t where t.status=?", Switch.ON);
	}

}
