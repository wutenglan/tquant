package com.goldskyer.tquant.storage.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.storage.api.DemoService;

public class DemoServiceImpl implements DemoService {

	private Log log = LogFactory.getLog(DemoServiceImpl.class);
	
	public String sayHello() {
		String name="ad";
		log.info("name = "+name);
		return "name is "+name;
	}

	@Override
	public String sayHello1(String name) {
		log.info("name = "+name);
		return "name is "+name;
	}

	

	

}
