package com.goldskyer.tquant.storage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.storage.dao.BaseDao;
import com.goldskyer.tquant.storage.entities.Weekend;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.service.WeekendService;




@SuppressWarnings("unchecked")
@Service
public class WeekendServiceImpl implements
		WeekendService {
	public static Map weekendsMap = new HashMap<String, String>();

	public void load()
	{
		Map<String, String> hsTmp = new HashMap<String, String>();
		List<Weekend> list = queryAllWeekends();
		for (Weekend w : list) {
			hsTmp.put(w.getExchange().name() + w.getDateString(), w.getDateString());
		}
		weekendsMap = hsTmp;
	}

	@Autowired
	private BaseDao baseDao;

	@Override
	public List<Weekend> queryAllWeekends()
	{
		return baseDao.query(
				"from Weekend order by dateString desc");
	}

	/**
	 * 获取上一个有效的交易日
	 * 
	 * @param dateString
	 * @return
	 */
	@Override
	public String getPreTradingDateString(Exchange exchange, String dateString)
	{
		String tmp = DateUtil.date2String(DateUtil.getDateBefore(
				DateUtil.string2Date(dateString), 1));
		while (!isValidTradingDateString(exchange, tmp))
		{
			tmp = DateUtil.date2String(DateUtil.getDateBefore(
					DateUtil.string2Date(tmp), 1));
		}
		return tmp;
	}

	@Override
	public String getNextTradingDateString(Exchange exchange, String dateString)
	{
		String tmp = DateUtil.date2String(DateUtil.getDateBefore(
				DateUtil.string2Date(dateString), -1));
		while (!isValidTradingDateString(exchange, tmp))
		{
			tmp = DateUtil.date2String(DateUtil.getDateBefore(
					DateUtil.string2Date(tmp), -1));
		}
		return tmp;
	}

	@Override
	public String getNextTradingDateString(Exchange exchange, String dateString, Integer interval)
	{
		if (interval == 0)
			return dateString;
		if (interval > 0) {
			String tmp = dateString;
			for (int i = 0; i < interval; i++) {
				tmp = getNextTradingDateString(exchange, tmp);
			}
			return tmp;
		} else {
			String tmp = dateString;
			for (int i = interval; i < 0; i++) {
				tmp = getPreTradingDateString(exchange, tmp);
			}
			return tmp;
		}
	}

	public boolean isValidTradingDateString(Exchange exchange, String dateString)
	{
		return !weekendsMap.containsKey(exchange.name() + dateString);
	}
}
