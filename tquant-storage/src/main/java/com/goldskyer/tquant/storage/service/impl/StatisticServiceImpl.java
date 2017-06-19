package com.goldskyer.tquant.storage.service.impl;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.dao.BaseDao;
import com.goldskyer.tquant.storage.entities.Statistic;
import com.goldskyer.tquant.storage.service.StatisticService;
@Service
public class StatisticServiceImpl  implements StatisticService{

	private BaseDao baseDao;
	@Override
	public void addStatistic(Statistic statistic) {
		baseDao.add(statistic);
	}

}
