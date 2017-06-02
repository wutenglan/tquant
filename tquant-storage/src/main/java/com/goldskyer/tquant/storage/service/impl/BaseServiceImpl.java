package com.goldskyer.tquant.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldskyer.tquant.storage.dao.HibernateBaseDao;

public class BaseServiceImpl
{
	@Autowired
	protected HibernateBaseDao baseDao;
}
