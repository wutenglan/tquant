package com.goldskyer.tquant.storage.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.storage.dao.HibernateBaseDao;
import com.goldskyer.tquant.storage.service.InstrumentService;

public class BaseBusinessImpl
{
	@Autowired
	protected HibernateBaseDao baseDao;


	@Autowired
	protected InstrumentService instrumentService;
}
