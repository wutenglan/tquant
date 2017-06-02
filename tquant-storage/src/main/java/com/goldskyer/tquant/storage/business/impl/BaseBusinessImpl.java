package com.goldskyer.tquant.storage.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.storage.dao.HibernateBaseDao;
import com.goldskyer.tquant.storage.service.InstrumentService;
import com.goldskyer.tquant.storage.service.ScsStorageService;

public class BaseBusinessImpl
{
	@Autowired
	protected HibernateBaseDao baseDao;
	
	@Autowired
	protected ScsStorageService scsStorageService;

	@Autowired
	protected InstrumentService instrumentService;
}
