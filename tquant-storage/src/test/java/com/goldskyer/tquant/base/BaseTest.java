package com.goldskyer.tquant.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldskyer.tquant.storage.dao.HibernateBaseDao;
import com.goldskyer.tquant.storage.service.InstrumentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tquant/storage/applicationContext.xml")
public class BaseTest
{
	protected Log log = LogFactory.getLog(BaseTest.class);
	@Autowired
	protected HibernateBaseDao baseDao;

	@Autowired
	protected InstrumentService instrumentService;

}
