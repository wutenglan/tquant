package com.goldskyer.tquant.storage.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.entities.Statistic;
import com.goldskyer.tquant.storage.monitor.vo.TickMonitorVo;
import com.goldskyer.tquant.storage.service.StatisticService;
import com.goldskyer.tquant.storage.sina.dto.SinaToady;
@Service
public class StatisticFacadeImpl {
	
	@Autowired
	private StatisticService statisticService;
	public void collect(TickMonitorVo tickMonitorVo)
	{
		if(tickMonitorVo.isMarketClose())
		{
			Statistic statistic=new Statistic();
			
			statisticService.addStatistic(statistic);
		}
	}
}
